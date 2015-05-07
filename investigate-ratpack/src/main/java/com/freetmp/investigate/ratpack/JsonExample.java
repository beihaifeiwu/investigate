package com.freetmp.investigate.ratpack;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import ratpack.guice.Guice;
import ratpack.http.client.ReceivedResponse;
import ratpack.jackson.JacksonModule;
import ratpack.test.embed.EmbeddedApp;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;
import static ratpack.jackson.Jackson.jsonNode;
import static ratpack.util.Types.listOf;

/**
 * Created by LiuPin on 2015/5/5.
 */
public class JsonExample {
  public static class Person {
    private final String name;

    public Person(@JsonProperty("name") String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public static void main(String... args) throws Exception {
    EmbeddedApp.of(s -> s
            .registry(Guice.registry(b -> b.add(JacksonModule.class, c -> c.prettyPrint(false))))
            .handlers(chain ->
                    chain.get(ctx -> ctx.render(json(new Person("John"))))
            )
    ).test(httpClient -> {
      ReceivedResponse response = httpClient.get();
      assertEquals("{\"name\":\"John\"}", response.getBody().getText());
      assertEquals("application/json", response.getBody().getContentType().getType());
    });

    EmbeddedApp.of(s -> s
            .registry(Guice.registry(b -> b.add(JacksonModule.class, c -> c.prettyPrint(false))))
            .handlers(chain -> chain
                    .post("asNode", ctx -> {
                      JsonNode node = ctx.parse(jsonNode());
                      ctx.render(node.get("name").asText());
                    })
                    .post("asPerson", ctx -> {
                      Person person = ctx.parse(fromJson(Person.class));
                      ctx.render(person.getName());
                    })
                    .post("asPersonList", ctx -> {
                      List<Person> person = ctx.parse(fromJson(listOf(Person.class)));
                      ctx.render(person.get(0).getName());
                    })
            )
    ).test(httpClient -> {
      ReceivedResponse response = httpClient.requestSpec(s ->
              s.body(b -> b.type("application/json").text("{\"name\":\"John\"}"))
      ).post("asNode");
      assertEquals("John", response.getBody().getText());

      response = httpClient.requestSpec(s ->
              s.body(b -> b.type("application/json").text("{\"name\":\"John\"}"))
      ).post("asPerson");
      assertEquals("John", response.getBody().getText());

      response = httpClient.requestSpec(s ->
              s.body(b -> b.type("application/json").text("[{\"name\":\"John\"}]"))
      ).post("asPersonList");
      assertEquals("John", response.getBody().getText());
    });

    EmbeddedApp.of(s -> s
            .registry(Guice.registry(b -> b.add(JacksonModule.class, c -> c.prettyPrint(false))))
            .handlers(chain -> chain
                    .post("asPerson", ctx -> {
                      Person person = ctx.parse(Person.class);
                      ctx.render(person.getName());
                    })
                    .post("asPersonList", ctx -> {
                      List<Person> person = ctx.parse(listOf(Person.class));
                      ctx.render(person.get(0).getName());
                    })
            )
    ).test(httpClient -> {
      ReceivedResponse response = httpClient.requestSpec(s ->
              s.body(b -> b.type("application/json").text("{\"name\":\"John\"}"))
      ).post("asPerson");
      assertEquals("John", response.getBody().getText());

      response = httpClient.requestSpec(s ->
              s.body(b -> b.type("application/json").text("[{\"name\":\"John\"}]"))
      ).post("asPersonList");
      assertEquals("John", response.getBody().getText());
    });

    EmbeddedApp.of(s -> s
            .registry(Guice.registry(b -> b
                    .add(JacksonModule.class, c -> c
                            .modules(new Jdk8Module()) // register the Jackson module
                            .prettyPrint(false)
                    )
            ))
            .handlers(chain ->
                    chain.get(ctx -> {
                      Optional<Person> personOptional = Optional.of(new Person("John"));
                      ctx.render(json(personOptional));
                    })
            )
    ).test(httpClient -> {
      ReceivedResponse response = httpClient.get();
      assertEquals("{\"name\":\"John\"}", response.getBody().getText());
      assertEquals("application/json", response.getBody().getContentType().getType());
    });
  }
}
