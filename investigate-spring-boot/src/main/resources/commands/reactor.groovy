package commands

import com.freetmp.investigate.crsh.AbstractCrashCommand
import com.freetmp.investigate.springboot.reactor.QuoteService
import org.crsh.cli.Command
import org.crsh.cli.Option
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.springframework.beans.factory.BeanFactory

@Usage("Reactor framework test command")
public class reactor extends AbstractCrashCommand {

    @Command
    @Usage("test reactor with quote fetch")
    def quote(
            @Usage("The quote fetch numbers") @Option(names = ["n", "num"]) Integer num,
            InvocationContext context) {
        runWithStdoutCapture(context) {
            if (num == null) num = 10;
            try {
                BeanFactory beanFactory = context.getAttributes()["spring.beanfactory"] as BeanFactory;
                QuoteService quoteService = beanFactory.getBean(QuoteService.class);
                quoteService.publishQuotes(num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}