package investigate.springboot.integration;

import com.google.common.util.concurrent.Uninterruptibles;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by liupin on 2015/8/20.
 */
@Configuration
public class CafeDemo {

  @Data @NoArgsConstructor
  public static class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SEPARATOR = "-----------------------";

    private List<Drink> deliveredDrinks;

    private int orderNumber;

    public Delivery(List<Drink> deliveredDrinks) {
      assert(deliveredDrinks.size() > 0);
      this.deliveredDrinks = deliveredDrinks;
      this.orderNumber = deliveredDrinks.get(0).getOrderNumber();
    }

    @Override
    public String toString() {
      StringBuilder buffer = new StringBuilder(SEPARATOR + "\n");
      buffer.append("Order #").append(getOrderNumber()).append("\n");
      for (Drink drink : getDeliveredDrinks()) {
        buffer.append(drink);
        buffer.append("\n");
      }
      buffer.append(SEPARATOR + "\n");
      return buffer.toString();
    }

  }

  @Data @NoArgsConstructor
  public static class Drink implements Serializable{

    private static final long serialVersionUID = 1L;

    private boolean iced;

    private int shots;

    private DrinkType drinkType;

    private int orderNumber;

    public Drink(int orderNumber, DrinkType drinkType, boolean iced, int shots) {
      this.orderNumber = orderNumber;
      this.drinkType = drinkType;
      this.iced = iced;
      this.shots = shots;
    }

    @Override
    public String toString() {
      return (iced?"Iced":"Hot")  + " " + drinkType.toString() + ", " + shots + " shots.";
    }

  }

  public enum DrinkType implements Serializable{

    ESPRESSO,
    LATTE,
    CAPPUCCINO,
    MOCHA

  }

  @Data @NoArgsConstructor
  public static class Order implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<OrderItem> items = new ArrayList<OrderItem>();

    /** the order number used for tracking */
    private int number;

    public Order(int number) {
      this.number = number;
    }

    public void addItem(DrinkType drinkType, int shots, boolean iced) {
      this.items.add(new OrderItem(this.number, drinkType, shots, iced));
    }
  }

  @Data @NoArgsConstructor
  public static class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private DrinkType drinkType;

    private int shots = 1;

    private boolean iced = false;

    /** the order this item is tied to */
    private int orderNumber;

    public OrderItem(int orderNumber, DrinkType drinkType, int shots, boolean iced) {
      this.orderNumber = orderNumber;
      this.drinkType = drinkType;
      this.shots = shots;
      this.iced = iced;
    }
  }


  @MessagingGateway
  public interface Cafe {

    @Gateway(requestChannel = "orders.input")
    void placeOrder(Order order);

  }

  private AtomicInteger hotDrinkCounter = new AtomicInteger();

  private AtomicInteger coldDrinkCounter = new AtomicInteger();

  @Bean(name = PollerMetadata.DEFAULT_POLLER)
  public PollerMetadata poller() {
    return Pollers.fixedDelay(1000).get();
  }

  @SuppressWarnings("deprecation")
  @Bean
  public IntegrationFlow orders() {
    return f -> f
        .split(Order.class, Order::getItems)
        .channel(c -> c.executor(Executors.newCachedThreadPool()))
        .<OrderItem, Boolean>route(OrderItem::isIced, mapping -> mapping
            .subFlowMapping("true", sf -> sf
                .channel(c -> c.queue(10))
                .publishSubscribeChannel(c -> c
                    .subscribe(s -> s.handle(m -> Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS)))
                    .subscribe(sub -> sub
                        .<OrderItem, String>transform(p ->
                            Thread.currentThread().getName()
                                + " prepared cold drink #" + this.coldDrinkCounter.incrementAndGet()
                                + " for order #" + p.getOrderNumber() + ": " + p)
                        .handle(m -> System.out.println(m.getPayload())))))
            .subFlowMapping("false", sf -> sf
                .channel(c -> c.queue(10))
                .publishSubscribeChannel(c -> c
                    .subscribe(s -> s.handle(m -> Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS)))
                    .subscribe(sub -> sub
                        .<OrderItem, String>transform(p ->
                            Thread.currentThread().getName()
                                + " prepared hot drink #" + this.hotDrinkCounter.incrementAndGet()
                                + " for order #" + p.getOrderNumber() + ": " + p)
                        .handle(m -> System.out.println(m.getPayload()))))))
        .<OrderItem, Drink>transform(orderItem ->
            new Drink(orderItem.getOrderNumber(),
                orderItem.getDrinkType(),
                orderItem.isIced(),
                orderItem.getShots()))
        .aggregate(aggregator -> aggregator
            .outputProcessor(g ->
                new Delivery(g.getMessages()
                    .stream()
                    .map(message -> (Drink) message.getPayload())
                    .collect(Collectors.toList())))
            .correlationStrategy(m -> ((Drink) m.getPayload()).getOrderNumber()), null)
        .handle(CharacterStreamWritingMessageHandler.stdout());
  }
}
