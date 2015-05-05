package com.freetmp.investigate.ratpack;

import ratpack.http.client.HttpClient;
import ratpack.test.embed.EmbeddedApp;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class HttpClientExample {

  public static void main(String... args) throws Exception {
    try(EmbeddedApp remoteApp = EmbeddedApp.fromHandler(ctx-> ctx.render("Hello From remoteApp"))){
      EmbeddedApp.fromHandler(ctx -> ctx
        .render(ctx.get(HttpClient.class).get(remoteApp.getAddress()).map(response->response.getBody().getText()))
      ).test(testHttpClient -> {
        assertThat(testHttpClient.get().getBody().getText()).isEqualTo("Hello From remoteApp");
      });
    }
  }

}
