package com.freetmp.investigate.ratpack;

import org.assertj.core.api.Assertions;
import ratpack.error.ServerErrorHandler;
import ratpack.test.embed.EmbeddedApp;

/**
 * Created by LiuPin on 2015/5/4.
 */
public class PartitionExample {

    public static void main(String[] args) throws Exception {
        EmbeddedApp.fromHandlers(chain -> chain
            .prefix("api", api -> api
                .register(r -> r.add(ServerErrorHandler.class,(context, throwable) ->
                        context.render("api error: " + throwable.getMessage())
                    )
                )
                .handler(ctx -> {
                    throw new Exception("in api - " + ctx.getRequest().getPath());
                })
            )
            .register(r -> r.add(ServerErrorHandler.class, (context,throwable)->{
                    context.render("app error: " + throwable.getMessage());
                })
            )
            .handler(ctx -> {
                throw new Exception("in app - " + ctx.getRequest().getPath());
            })
        ).test(httpClient ->{
            Assertions.assertThat(httpClient.get("api/foo").getBody().getText()).isEqualTo("api error: in api - api/foo");
            Assertions.assertThat(httpClient.get("bar").getBody().getText()).isEqualTo("app error: in app - bar");
        });
    }
}
