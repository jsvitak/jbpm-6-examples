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

import com.google.inject.Inject;
import org.jbpm.examples.ejb.TaskBean;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.List;

@Model
public class TaskController {

    @EJB
    TaskBean taskBean;

    @Inject
    FacesContext facesContext;

    private String user;
    private List<TaskSummary> tasks;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    private Task task;

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    private long taskId;


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
        try {
            tasks = taskBean.retrieveTaskList(user);
        } catch (Exception e) {
            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot retrieve tasks.", "Fail!");
            FacesContext.getCurrentInstance().addMessage(null, m);
        }
    }

    public void queryTask() {
        try {
            task = taskBean.getTask(taskId);
        } catch (Exception e) {
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to query for task with id = " + taskId, "Fail!");
            FacesContext.getCurrentInstance().addMessage(null, fm);
            e.printStackTrace();
        }
    }

    public String approveTask() {
        FacesMessage fm;
        try {
            taskBean.approveTask(user, taskId);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, "The task has been successfully approved.", "Success :)");
        } catch (Exception e) {
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to approve the task.", "Fail!");
        }
        FacesContext.getCurrentInstance().addMessage(null, fm);
        return "index.xhtml";
    }
}
