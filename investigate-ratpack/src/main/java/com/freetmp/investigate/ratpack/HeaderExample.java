package com.freetmp.investigate.ratpack;

import ratpack.http.Headers;
import ratpack.http.MutableHeaders;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.embed.EmbeddedApp;

import static org.junit.Assert.assertEquals;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class HeaderExample {

  public static void main(String... args) throws Exception {
    EmbeddedApp
        .fromHandler(ctx -> {
          Headers headers = ctx.getRequest().getHeaders();
          String clientHeader = headers.get("Client-Header");
          ctx.getResponse().send(clientHeader);
        })
        .test(httpClient -> {
          ReceivedResponse receivedResponse = httpClient
              .requestSpec(requestSpec ->
                      requestSpec.getHeaders().set("Client-Header", "From Client")
              ).get();

          assertEquals("From Client", receivedResponse.getBody().getText());
        });

    EmbeddedApp
        .fromHandler(ctx -> {
          MutableHeaders headers = ctx.getResponse().getHeaders();
          headers.add("Custom-Header", "custom-header-value");
          ctx.getResponse().send("ok");
        })
        .test(httpClient -> {
          ReceivedResponse receivedResponse = httpClient.get();
          assertEquals("custom-header-value", receivedResponse.getHeaders().get("Custom-Header"));
        });
  }
}
