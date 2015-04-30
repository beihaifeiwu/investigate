package com.freetmp.investigate.ratpack;

import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import java.net.URI;

/**
 * Created by LiuPin on 2015/4/30.
 */
public class RatpackServerMain {

    public static void main(String... args) throws Exception {
        RatpackServer.start(server->server.serverConfig(ServerConfig.embedded().publicAddress(new URI("http://localhost")))
                .registryOf(registry -> registry.add("World!"))
                .handlers(chain -> chain.get(ctx->ctx.render("Hello " + ctx.get(String.class)))
                            .get(":name",ctx1 -> ctx1.render("Hello " + ctx1.getPathTokens()))
                )
        );
    }
}
