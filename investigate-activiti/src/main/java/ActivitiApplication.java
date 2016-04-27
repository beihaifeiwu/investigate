import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * Created by sifan on 2016/4/26.
 */
public class ActivitiApplication {
    public static void main(String[] args) {
        System.setProperty(Weld.ARCHIVE_ISOLATION_SYSTEM_PROPERTY, "false");
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

//        container.instance().select(ManualActiviti.class).get();
//        container.instance().select(CdiActiviti.class).get();

        container.event().fire(Status.START_PROCESS);
    }
}
