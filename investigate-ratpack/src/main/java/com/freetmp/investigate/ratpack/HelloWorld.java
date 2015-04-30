package com.freetmp.investigate.ratpack;

import com.google.common.io.Files;
import ratpack.test.embed.EmbeddedApp;
import org.assertj.core.api.Assertions;
import ratpack.test.exec.ExecHarness;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * Created by LiuPin on 2015/4/30.
 */
public class HelloWorld {

    public static void main(String[] args) throws Exception {
        EmbeddedApp.fromHandler(ctx -> ctx.render("Hello World!"))
            .test(testHttpClient -> Assertions.assertThat(testHttpClient.getText()).isEqualTo("Hello World!"));

        File tmpFile = File.createTempFile("ratpack","test");
        Files.write("Hello World!", tmpFile, StandardCharsets.UTF_8);
        tmpFile.deleteOnExit();

        String content = ExecHarness.yieldSingle(execControl -> execControl.blocking(()->Files.toString(tmpFile,StandardCharsets.UTF_8)))
                                    .getValueOrThrow();
        Assertions.assertThat(content).isEqualTo("Hello World!");
    }
}
