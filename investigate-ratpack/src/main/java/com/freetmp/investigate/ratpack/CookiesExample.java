package com.freetmp.investigate.ratpack;

import io.netty.handler.codec.http.Cookie;
import org.assertj.core.api.Assertions;
import ratpack.http.client.ReceivedResponse;
import ratpack.test.embed.EmbeddedApp;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class CookiesExample {

  public static void main(String[] args) throws Exception {
    EmbeddedApp.fromHandler(ctx -> {
      String username = ctx.getRequest().oneCookie("username");
      ctx.getResponse().send("Welcome to Ratpack, " + username);
    }).test(testHttpClient -> {
      ReceivedResponse response = testHttpClient.requestSpec(requestSpec -> {
        requestSpec.getHeaders().set("Cookie","username=hbogart1");
      }).get();
      Assertions.assertThat(response.getBody().getText()).isEqualTo("Welcome to Ratpack, hbogart1");
    });

    EmbeddedApp.fromHandler(ctx -> {
      Set<Cookie> cookies = ctx.getRequest().getCookies();
      assertEquals(1, cookies.size());
      Cookie cookie = cookies.iterator().next();
      assertEquals("username", cookie.name());
      assertEquals("hbogart1", cookie.value());
      ctx.getResponse().send("Welcome to Ratpack, " + cookie.value() + "!");
    }).test(httpClient -> {
      ReceivedResponse response = httpClient
          .requestSpec(requestSpec -> requestSpec
              .getHeaders()
              .set("Cookie", "username=hbogart1"))
          .get();

      assertEquals("Welcome to Ratpack, hbogart1!", response.getBody().getText());
    });

    EmbeddedApp.fromHandler(ctx -> {
      assertTrue(ctx.getResponse().getCookies().isEmpty());
      ctx.getResponse().cookie("whiskey", "make-it-rye");
      assertEquals(1, ctx.getResponse().getCookies().size());
      ctx.getResponse().send("ok");
    }).test(httpClient -> {
      ReceivedResponse response = httpClient.get();
      assertEquals("whiskey=make-it-rye", response.getHeaders().get("Set-Cookie"));
    });

    EmbeddedApp.fromHandler(ctx -> {
      ctx.getResponse().expireCookie("username");
      ctx.getResponse().send("ok");
    }).test(httpClient -> {
      ReceivedResponse response = httpClient
          .requestSpec(requestSpec -> requestSpec
                  .getHeaders().set("Cookie", "username=lbacall1")
          )
          .get();

      String setCookie = response.getHeaders().get("Set-Cookie");
      assertTrue(setCookie.startsWith("username=; Max-Age=0"));
    });
  }
}
