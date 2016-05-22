package commands

import com.freetmp.investigate.crsh.AbstractCrashCommand
import com.freetmp.investigate.springboot.integration.CafeDemo
import com.freetmp.investigate.springboot.integration.SimpleStart
import org.crsh.cli.Argument
import org.crsh.cli.Command
import org.crsh.cli.Required
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.springframework.beans.factory.BeanFactory

import java.util.concurrent.atomic.AtomicInteger

@Usage("Spring Integration framework test command")
public class integration extends AbstractCrashCommand {

    @Command
    @Usage("Transform input to uppercase")
    def upcase(
            @Required @Argument List<String> args,
            InvocationContext context) {
        runWithStdoutCapture(context) {
            try {
                BeanFactory beanFactory = context.getAttributes()["spring.beanfactory"] as BeanFactory;
                SimpleStart.Upcase bean = beanFactory.getBean(SimpleStart.Upcase.class);
                bean.upcase(args).each { println it }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static final AtomicInteger orderNum = new AtomicInteger(1)

    @Command
    @Usage("Cafe order process flow mocking")
    def cafe(InvocationContext context) {

        runWithStdoutCapture(context) {
            try {
                BeanFactory beanFactory = context.getAttributes()["spring.beanfactory"] as BeanFactory;
                CafeDemo.Cafe cafe = beanFactory.getBean(CafeDemo.Cafe.class);
                println "Please order your cafe:";

                boolean another = true;
                CafeDemo.Order order = new CafeDemo.Order(orderNum.getAndIncrement());
                CafeDemo.DrinkType[] values = CafeDemo.DrinkType.values();
                while (another) {

                    String msg = String.format("Which type do you want ? [%s]: ", values.collect { "${it.ordinal()}: ${it.name()}" }.join(","));
                    String ordinal = readInCandidates(context, msg, values*.ordinal().collect { it.toString() }.toArray(new String[values.size()]));
                    CafeDemo.DrinkType drinkType = CafeDemo.DrinkType.values()[Integer.parseInt(ordinal)];

                    Integer num = readInt(context, "How much do you want ?: ")
                    boolean iced = readBoolean(context, "Is cafe iced or not ? ");

                    order.addItem(drinkType, num, iced);

                    another = readBoolean(context, "Want to order another item ? ");
                }

                if (order.getItems() == null || order.getItems().isEmpty()) {
                    println "The order is empty, nothing will happen!"
                } else {
                    println "The order is being processed, please wait!"
                    cafe.placeOrder order
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
