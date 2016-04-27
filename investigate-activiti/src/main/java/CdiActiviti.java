import org.activiti.cdi.BusinessProcessEvent;
import org.activiti.cdi.annotation.event.BusinessProcess;

import javax.enterprise.event.Observes;

/**
 * Created by sifan on 2016/4/26.
 */
public class CdiActiviti {

    public void onProcessEvent(@Observes @BusinessProcess("financialReport") BusinessProcessEvent businessProcessEvent) {
        businessProcessEvent.getVariableScope();
        System.out.println("business process event: " + businessProcessEvent.getActivityId());
    }
}
