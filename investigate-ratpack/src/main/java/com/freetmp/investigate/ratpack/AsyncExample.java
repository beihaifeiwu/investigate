package com.freetmp.investigate.ratpack;

import org.assertj.core.api.Assertions;
import ratpack.handling.Context;
import ratpack.handling.InjectionHandler;
import ratpack.test.embed.EmbeddedApp;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class AsyncExample {

  public static interface Datastore {
    Integer deleteOlderThan(int days) throws IOException;
  }

  public static class DeletingHandler extends InjectionHandler{
    void handle(final Context context,final Datastore datastore){
      final int days = context.getPathTokens().asInt("days");
      context.blocking(()->datastore.deleteOlderThan(days))
              .then(i -> context.render(i + " records deleted"));
    }
  }

  public static void main(String[] args) throws Exception {
    HandlingResult result = RequestFixture.handle(new DeletingHandler(),fixture -> fixture
      .pathBinding(Collections.singletonMap("days","10"))
      .registry(r -> r.add(Datastore.class,days -> days))
    );

    Assertions.assertThat(result.rendered(String.class)).isEqualTo("10 records deleted");

    EmbeddedApp.fromHandler(ctx -> {
      ctx.promise(f -> new Thread(()-> f.success("HelloWorld")).start()).then(ctx::render);
    }).test(testHttpClient -> {
      Assertions.assertThat(testHttpClient.getText()).isEqualTo("HelloWorld");
    });
  }
}
