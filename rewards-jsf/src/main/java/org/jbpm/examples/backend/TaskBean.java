/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.examples.backend;

import org.jbpm.services.task.exception.PermissionDeniedException;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.task.api.InternalTaskService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.util.List;
import java.util.Map;

@RequestScoped
public class TaskBean {

    @Inject
    TaskService taskService;

    private Map<String,Object> content;

    public List<TaskSummary> retrieveTaskList(String actorId) throws Exception {
        UserTransaction ut = (UserTransaction) new InitialContext().lookup( "java:comp/UserTransaction" );
        ut.begin();
        List<TaskSummary> list;
        try {
            list = taskService.getTasksAssignedAsPotentialOwner(actorId, "en-UK");
            ut.commit();
        } catch (RollbackException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void approveTask(String actorId, long taskId, Map<String,Object> content) throws Exception {
        UserTransaction ut = (UserTransaction) new InitialContext().lookup( "java:comp/UserTransaction" );
        ut.begin();
        try {
            taskService.start(taskId, actorId);
            taskService.complete(taskId, actorId, content);
            ut.commit();
        } catch (RollbackException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause instanceof OptimisticLockException) {
                // Concurrent access to the same process instance
                throw new ProcessOperationException("The same process instance has likely been accessed concurrently",
                        e);
            }
            throw new RuntimeException(e);
        } catch (PermissionDeniedException e) {
            // Transaction might be already rolled back by TaskServiceSession
            if (ut.getStatus() == Status.STATUS_ACTIVE) {
                ut.rollback();
            }
            // Probably the task has already been started by other users
            throw new ProcessOperationException("The task (id = " + taskId
                    + ") has likely been started by other users ", e);
        } catch (Exception e) {
            // Transaction might be already rolled back by TaskServiceSession
            if (ut.getStatus() == Status.STATUS_ACTIVE) {
                ut.rollback();
            }
            throw new RuntimeException(e);
        } 
    }

    public Task getTask(long taskId) throws Exception {
        UserTransaction ut = (UserTransaction) new InitialContext().lookup( "java:comp/UserTransaction" );
        ut.begin();
        Task task;
        try {
            task = taskService.getTaskById(taskId);
            content = ((InternalTaskService) taskService).getTaskContent(taskId);
            ut.commit();
        } catch (Exception e) {
            ut.rollback();
            throw new ProcessOperationException("Cannot get task " + taskId, e);
        }
        return task;
    }

    public Map<String,Object> getContent() {
        return content;
    }

}
