package org.jbpm.quickstarts.humantask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.test.JBPMHelper;
import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.api.task.TaskService;
import org.kie.api.task.UserGroupCallback;
import org.kie.api.task.model.TaskSummary;

public class Evaluation {

    public static void main(String[] args) {

        // Prepare datasource
        JBPMHelper.startH2Server();
        JBPMHelper.setupDataSource();
        
        // Setup KieSession and TaskService
        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get().newDefaultBuilder()
                .userGroupCallback(new UserGroupCallback(){

                    @Override
                    public boolean existsUser(String userId) {                       
                        return true;
                    }

                    @Override
                    public boolean existsGroup(String groupId) {
                        return true;
                    }

                    @Override
                    public List<String> getGroupsForUser(String userId, List<String> groupIds, List<String> allExistingGroupIds) {
                        List<String> result = new ArrayList<>();
                        if(userId.equals("john")){
                            result.add("PM");
                        } else if(userId.equals("mary")) {
                            result.add("HR");
                        }
                        return result;
                    }})
                .addAsset(KieServices.Factory.get().getResources().newClassPathResource("quickstarts/humantask/Evaluation.bpmn2"), ResourceType.BPMN2)
                .get();
        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
        RuntimeEngine engine = manager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        TaskService taskService = engine.getTaskService();
        
        // Start a new process instance
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("employee", "kylin");
        params.put("reason", "Yearly performance evaluation");
        ksession.startProcess("org.jbpm.quickstarts.Evaluation", params);
        System.out.println("Process started ...");
        
        // Complete Self Evaluation
        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner("kylin", "en-UK");
        TaskSummary task = tasks.get(0);
        System.out.println("'kylin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "kylin");
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("performance", "exceeding");
        taskService.complete(task.getId(), "kylin", results);
        
        // Complete PM Evaluation
        tasks = taskService.getTasksAssignedAsPotentialOwner("john", "en-UK");
        task = tasks.get(0);
        System.out.println(" 'john' completing task " + task.getName() + ": " + task.getDescription());
        taskService.claim(task.getId(), "john");
        taskService.start(task.getId(), "john");
        results = new HashMap<String, Object>();
        results.put("performance", "acceptable");
        taskService.complete(task.getId(), "john", results);
        
        // Complete HR Evaluation
        tasks = taskService.getTasksAssignedAsPotentialOwner("mary", "en-UK");
        task = tasks.get(0);
        System.out.println(" 'mary' completing task " + task.getName() + ": " + task.getDescription());
        taskService.claim(task.getId(), "mary");
        taskService.start(task.getId(), "mary");
        results = new HashMap<String, Object>();
        results.put("performance", "outstanding");
        taskService.complete(task.getId(), "mary", results);
        
        System.out.println("Process instance completed");
        
        ksession.dispose();
        manager.disposeRuntimeEngine(engine);
        manager.close();
        
    }

}
