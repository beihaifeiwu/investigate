package spring.statemachine.turnstile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.stereotype.Component;
import spring.statemachine.common.AbstractStateMachineCommands;
import spring.statemachine.common.CommonConfiguration;

import java.util.EnumSet;

/**
 * Created by LiuPin on 2015/9/24.
 */
@SpringBootApplication
@Import(CommonConfiguration.class)
public class Turnstile {

  public enum States { LOCKED, UNLOCKED }

  public enum Events { COIN, PUSH }

  @Configuration
  @EnableStateMachine
  static class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {
    @Override public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
      states
          .withStates()
            .initial(States.LOCKED)
            .states(EnumSet.allOf(States.class));
    }

    @Override public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
      transitions
          .withExternal()
            .source(States.LOCKED)
            .target(States.UNLOCKED)
            .event(Events.COIN)
            .and()
          .withExternal()
            .source(States.UNLOCKED)
            .target(States.LOCKED)
            .event(Events.PUSH);
    }
  }

  @Component
  public class StateMachineCommands extends AbstractStateMachineCommands<States, Events> {

    @CliCommand(value = "sm event", help = "Sends an event to a state machine")
    public String event(@CliOption(key = { "", "event" }, mandatory = true, help = "The event") final Events event) {
      getStateMachine().sendEvent(event);
      return "Event " + event + " send";
    }

  }

  public static void main(String[] args) {
    System.setProperty("chart.path", "turnstile.txt");
    SpringApplication.run(Turnstile.class, args);
  }
}
