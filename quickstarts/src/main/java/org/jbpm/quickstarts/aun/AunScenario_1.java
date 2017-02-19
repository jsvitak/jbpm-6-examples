package org.jbpm.quickstarts.aun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
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

/**
 * 
 * Scenario One:
 *  1. Open 
 *  2. TelecommunterApproval 
 *  3. Open
 *  4. LineMgmtApproval
 *  5. Open
 *  6. L5L6Approval
 *  7. HR Processing
 *  
 * @author kylin
 *
 */
public class AunScenario_1 {

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
                        
                        return result;
                    }})
                .addAsset(KieServices.Factory.get().getResources().newClassPathResource("quickstarts/aun/Aun.bpmn2"), ResourceType.BPMN2)
                .get();
        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
        RuntimeEngine engine = manager.getRuntimeEngine(null);
        KieSession ksession = engine.getKieSession();
        ksession.getWorkItemManager().registerWorkItemHandler("Email", new SystemOutWorkItemHandler());
        TaskService taskService = engine.getTaskService();
        
        // Start a new process instance
        ksession.startProcess("org.jbpm.quickstarts.Aun");
        
        /**
         * 1. Open -> enter 'User1'
         */
        List<TaskSummary> tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        TaskSummary task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("_user", "User1");
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 2. Telecommunter Approve -> set to false
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        results.put("_isTelecommunterApproval", false);
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 3. Open -> enter 'User2'
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        results.put("_user", "User2");
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 4. LineMgmtApproval -> set to false
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        results.put("_isLineMgmtApproval", false);
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 5. Open -> enter 'User3'
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        results.put("_user", "User3");
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 6. L5L6Approval -> set to true
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        results.put("_isL5L6Approval", true);
        taskService.complete(task.getId(), "admin", results);
        
        /**
         * 7. HR Processing
         */
        tasks = taskService.getTasksAssignedAsPotentialOwner("admin", "en-UK");
        task = tasks.get(0);
        System.out.println("'admin' completing task " + task.getName() + ": " + task.getDescription());
        taskService.start(task.getId(), "admin");
        results = new HashMap<String, Object>();
        taskService.complete(task.getId(), "admin", results);

        ksession.dispose();
        manager.disposeRuntimeEngine(engine);
        manager.close();
        
    }

}
