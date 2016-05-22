package com.freetmp.investigate.ratpack;

import io.netty.handler.codec.http.HttpResponseStatus;
import org.assertj.core.api.Assertions;
import ratpack.handling.Context;
import ratpack.http.Response;
import ratpack.http.Status;
import ratpack.http.client.ReceivedResponse;
import ratpack.http.internal.DefaultStatus;
import ratpack.render.RendererSupport;
import ratpack.test.embed.EmbeddedApp;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class ResponseExample {

    static class Foo {
        public String value;
    }

    static class FooRender extends RendererSupport<Foo> {

        @Override public void render(Context context, Foo foo) throws Exception {
            context.getResponse().send("Custom drinkType: Foo, value=" + foo.value);
        }
    }

    public static void main(String[] args) throws Exception {
        EmbeddedApp.fromHandlers(chain -> chain
            .handler("status-object", ctx -> {
                Status status = new DefaultStatus(HttpResponseStatus.FOUND);
                Response response = ctx.getResponse();
                response.status(status).send("foo");
            })
            .handler(":status",ctx->{
                Response response = ctx.getResponse();
                String status = ctx.getPathTokens().get("status");
                response.status(Integer.parseInt(status));
                response.send("foo");
            })
            .handler("render/null",ctx->{
                Foo foo = new Foo();
                foo.value = "bar";
                ctx.render(foo);
            })
        ).test(testHttpClient -> {
            Assertions.assertThat(testHttpClient.get("200").getStatusCode()).isEqualTo(200);
            Assertions.assertThat(testHttpClient.get("301").getStatusCode()).isEqualTo(301);
            Assertions.assertThat(testHttpClient.get("404").getStatusCode()).isEqualTo(404);
            Assertions.assertThat(testHttpClient.get("503").getStatusCode()).isEqualTo(503);

            Assertions.assertThat(testHttpClient.get("status-object").getStatusCode()).isEqualTo(302);

            Assertions.assertThat(testHttpClient.get("render/null").getStatusCode()).isEqualTo(500);
        });

        EmbeddedApp.fromHandlers(chain -> chain
            .register(new FooRender().register())
            .handler(ctx -> {
                Foo foo = new Foo();
                foo.value = "bar";
                ctx.render(foo);
            })
        ).test(httpClient ->{
            ReceivedResponse response = httpClient.get();
            Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
            Assertions.assertThat(response.getBody().getText()).isEqualTo("Custom drinkType: Foo, value=bar");
        });
    }
}
