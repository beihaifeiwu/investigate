import org.activiti.cdi.spi.ProcessEngineLookup;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by sifan on 2016/4/25.
 */
@Singleton
public class ManualActiviti {

    @Inject
    ProcessEngineLookup processEngineLookup;

    private ProcessEngine processEngine;
    private RuntimeService runtimeService;
    private TaskService taskService;

    @PostConstruct
    public void init() {
        processEngine = processEngineLookup.getProcessEngine();
        runtimeService = processEngine.getRuntimeService();
        taskService = processEngine.getTaskService();
    }

    public void handle(@Observes Status status) {
        startProcessByKey("interviewProcess");
        startProcessByKey("financialReport");

        while (getTaskListAndHandle() > 0) {
            getTaskListAndHandle();
        }

        HistoryService historyService = processEngine.getHistoryService();
        historyService.createHistoricActivityInstanceQuery();
    }

    private ProcessInstance startProcessByKey(String processDefinitionKey) {
        ProcessInstance process = (ProcessInstance) runtimeService.createExecutionQuery().processDefinitionKey(processDefinitionKey).singleResult();
        if (process == null) {
            process = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        }

        return process;
    }

    private int getTaskListAndHandle() {
        List<Task> taskList = taskService.createTaskQuery().list();

        for (Task task : taskList) {
            System.out.println("handle task, task name:" + task.getName());
            taskService.complete(task.getId());
        }

        return taskList.size();
    }
}
