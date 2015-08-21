package com.freetmp.investigate.springboot.integration;

import com.google.common.util.concurrent.Uninterruptibles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.stream.CharacterStreamWritingMessageHandler;
import org.springframework.stereotype.Component;

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

  public static class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String SEPARATOR = "-----------------------";

    private List<Drink> deliveredDrinks;

    private int orderNumber;

    // Default constructor required by Jackson Java JSON-processor
    public Delivery() {}

    public Delivery(List<Drink> deliveredDrinks) {
      assert(deliveredDrinks.size() > 0);
      this.deliveredDrinks = deliveredDrinks;
      this.orderNumber = deliveredDrinks.get(0).getOrderNumber();
    }


    public int getOrderNumber() {
      return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
      this.orderNumber = orderNumber;
    }

    public List<Drink> getDeliveredDrinks() {
      return deliveredDrinks;
    }

    public void setDeliveredDrinks(List<Drink> deliveredDrinks) {
      this.deliveredDrinks = deliveredDrinks;
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

  public static class Drink implements Serializable{

    private static final long serialVersionUID = 1L;

    private boolean iced;

    private int shots;

    private DrinkType drinkType;

    private int orderNumber;

    // Default constructor required by Jackson Java JSON-processor
    public Drink() {}

    public Drink(int orderNumber, DrinkType drinkType, boolean iced, int shots) {
      this.orderNumber = orderNumber;
      this.drinkType = drinkType;
      this.iced = iced;
      this.shots = shots;
    }


    public int getOrderNumber() {
      return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
      this.orderNumber = orderNumber;
    }

    public boolean isIced() {
      return this.iced;
    }

    public void setIced(boolean iced) {
      this.iced = iced;
    }

    public DrinkType getDrinkType() {
      return this.drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
      this.drinkType = drinkType;
    }

    public int getShots() {
      return this.shots;
    }

    public void setShots(int shots) {
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

  public static class Order implements Serializable{

    private static final long serialVersionUID = 1L;

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    /** the order number used for tracking */
    private int number;

    // Default constructor required by Jackson Java JSON-processor
    public Order() {}

    public Order(int number) {
      this.number = number;
    }

    public void addItem(DrinkType drinkType, int shots, boolean iced) {
      this.orderItems.add(new OrderItem(this.number, drinkType, shots, iced));
    }

    public int getNumber() {
      return number;
    }

    public void setNumber(int number) {
      this.number = number;
    }

    public List<OrderItem> getItems() {
      return this.orderItems;
    }

    public void setItems(List<OrderItem> orderItems) {
      this.orderItems = orderItems;
    }
  }

  public static class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private DrinkType type;

    private int shots = 1;

    private boolean iced = false;

    /** the order this item is tied to */
    private int orderNumber;

    // Default constructor required by Jackson Java JSON-processor
    public OrderItem() {}

    public OrderItem(int orderNumber, DrinkType type, int shots, boolean iced) {
      this.orderNumber = orderNumber;
      this.type = type;
      this.shots = shots;
      this.iced = iced;
    }

    public int getOrderNumber() {
      return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
      this.orderNumber = orderNumber;
    }

    public boolean isIced() {
      return this.iced;
    }

    public void setIced(boolean iced) {
      this.iced = iced;
    }

    public int getShots() {
      return shots;
    }

    public void setShots(int shots) {
      this.shots = shots;
    }

    public DrinkType getDrinkType() {
      return this.type;
    }

    public void setDrinkType(DrinkType type) {
      this.type = type;
    }

    public String toString() {
      return ((this.iced) ? "iced " : "hot ") + this.shots + " shot " + this.type;
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

  @Component @org.springframework.core.annotation.Order(101)
  public static class CafeRun implements CommandLineRunner {

    @Autowired Cafe cafe;

    @Override
    public void run(String... args) throws Exception {
      System.out.println("************Spring Integration Cafe run***********");
      for (int i = 1; i <= 100; i++) {
        Order order = new Order(i);
        order.addItem(DrinkType.LATTE, 2, false);
        order.addItem(DrinkType.MOCHA, 3, true);
        cafe.placeOrder(order);
      }
    }
  }
}
