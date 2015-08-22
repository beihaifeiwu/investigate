package com.freetmp.investigate.ratpack;

import org.assertj.core.api.Assertions;
import ratpack.test.embed.EmbeddedApp;

import static ratpack.registry.Registries.*;
/**
 * Created by LiuPin on 2015/4/30.
 */
public class ContextExample {

    public interface Person {
        String getId();
        String getStatus();
        String getAge();
    }

    public static class PersonImpl implements Person {

        private final String id;
        private final String status;
        private final String age;

        public PersonImpl(String id, String status, String age){
            this.id = id;
            this.status = status;
            this.age = age;
        }

        @Override public String getId() {
            return id;
        }

        @Override public String getStatus() {
            return status;
        }

        @Override public String getAge() {
            return age;
        }
    }

    public static void main(String[] args) throws Exception {
        EmbeddedApp.fromHandlers(chain ->
                chain.prefix("person/:id",(personChain)->
                    personChain.handler(context ->{
                        String id = context.getPathTokens().get("id");
                        Person person = new PersonImpl(id,"example-status","example-age");
                        context.next(just(Person.class,person));
                    }).get("status",context->{
                        Person person = context.get(Person.class);
                        context.render("person " + person.getId() + " status: " + person.getStatus());
                    }).get("age",context->{
                        Person person = context.get(Person.class);
                        context.render("person " + person.getId() + " age: " + person.getAge());
                    })
                )
        ).test(httpClient ->{
            Assertions.assertThat(httpClient.get("person/10/status").getBody().getText()).isEqualTo("person 10 status: example-status");
            Assertions.assertThat(httpClient.get("person/6/age").getBody().getText()).isEqualTo("person 6 age: example-age");
        });
    }
}
