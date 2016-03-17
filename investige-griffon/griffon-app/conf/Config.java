import griffon.util.AbstractMapResourceBundle;

import javax.annotation.Nonnull;
import java.util.Map;

import static java.util.Arrays.asList;
import static griffon.util.CollectionUtils.map;

public class Config extends AbstractMapResourceBundle {
    @Override
    protected void initialize(@Nonnull Map<String, Object> entries) {
        map(entries)
            .e("application", map()
                .e("title", "investige-griffon")
                .e("startupGroups", asList("griffonApplication"))
                .e("autoShutdown", true)
            )
            .e("mvcGroups", map()
                .e("griffonApplication", map()
                    .e("model", "com.freetmp.investigate.griffon.GriffonApplicationModel")
                    .e("view", "com.freetmp.investigate.griffon.GriffonApplicationView")
                    .e("controller", "com.freetmp.investigate.griffon.GriffonApplicationController")
                )
            );
    }
}