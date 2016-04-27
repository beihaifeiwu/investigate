import org.activiti.engine.RepositoryService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by sifan on 2016/4/27.
 */
public class ProcessList {

    @Inject
    private RepositoryService repositoryService;

    @Produces
    @Named("processDefinitionList")
    public List getProcessDefinitionList() {
        return repositoryService.createProcessDefinitionQuery().list();
    }
}
