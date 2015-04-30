package com.freetmp.investigate.ratpack;

import ratpack.test.embed.EmbeddedApp;

/**
 * Created by LiuPin on 2015/4/30.
 */
public class HelloWorld {

    public static void main(String[] args) {
        EmbeddedApp.fromHandler(ctx -> ctx.render("Hello World!"))
            ;
    }
}
