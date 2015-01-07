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

package org.jbpm.examples.web;

import org.jbpm.examples.backend.TaskBean;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
public class TaskController {

    @Inject
    TaskBean taskBean;

    @Inject
    FacesContext facesContext;

    @Inject
    Logger logger;

    private String comment;
    private Map<String,Object> content;
    private Task task;
    private long taskId;
    private String user;
    private List<TaskSummary> tasks;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Produces
    public List<TaskSummary> getTasks() {
        return tasks;
    }


    public void retrieveTasks () {
        String message;
        try {
            tasks = taskBean.retrieveTaskList(user);
            message = "Retrieved " + tasks.size() + " task(s) for user " + user + ".";
            logger.info(message);
        } catch (Exception e) {
            message = "Cannot retrieve task list for user " + user + ".";
            logger.log(Level.SEVERE, message, e);
            facesContext.getExternalContext().getFlash()
                    .put("msg", message);
        }
    }

    public void queryTask() {
        String message;
        try {
            task = taskBean.getTask(taskId);
            content = taskBean.getContent();
            message = "Loaded task " + taskId + ".";
            logger.info(message);
        } catch (Exception e) {
            message = "Unable to query for task with id = " + taskId;
            logger.log(Level.SEVERE, message, e);
            facesContext.getExternalContext().getFlash()
                    .put("msg", message);
        }
    }

    public String approveTask() {
        String message;
        try {
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("out_comment", comment);
            taskBean.approveTask(user, taskId, result);
            message = "The task " + taskId + " has been successfully approved.";
            logger.info(message);
        } catch (Exception e) {
            message = "Unable to approve the task " + taskId + ".";
            logger.log(Level.SEVERE, message, e);
        }
        facesContext.getExternalContext().getFlash()
                .put("msg", message);
        return "index.xhtml?faces-redirect=true";
    }
}
