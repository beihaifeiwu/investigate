package com.freetmp.investigate.ratpack;

import ratpack.error.ServerErrorHandler;
import ratpack.exec.Promise;
import ratpack.rx.RxRatpack;
import ratpack.test.handling.HandlingResult;
import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static ratpack.rx.RxRatpack.observe;
import static ratpack.test.handling.RequestFixture.requestFixture;

/**
 * Created by LiuPin on 2015/5/5.
 */
public class RxJavaExample {

  public static void main(String[] args) throws Exception {
    RxRatpack.initialize();

    HandlingResult result = requestFixture().handle(context ->{
      Promise<String> promise = context.blocking(()->"Hello World");
      observe(promise).map(String::toUpperCase).subscribe(context::render);
    });

    assertThat(result.rendered(String.class)).isEqualTo("HELLO WORLD");

    result = requestFixture().handleChain(chain -> {
      chain.register(registry ->
        registry.add(ServerErrorHandler.class,(context, throwable) ->
          context.render("caught by error handler: " + throwable.getMessage())
        )
      );
      chain.get(context -> Observable.<String>error(new Exception("!")).subscribe((s)->{}));
    });
    assertThat(result.rendered(String.class)).isEqualTo("caught by error handler: !");
  }
}
