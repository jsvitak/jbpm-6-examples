/**
 * Copyright 2015, Red Hat, Inc.
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

import org.jbpm.examples.util.StartupBean;
import org.jbpm.services.ejb.api.RuntimeDataServiceEJBLocal;
import org.jbpm.services.ejb.api.UserTaskServiceEJBLocal;
import org.jbpm.services.task.commands.CompleteTaskCommand;
import org.jbpm.services.task.commands.CompositeCommand;
import org.jbpm.services.task.commands.StartTaskCommand;
import org.kie.api.task.model.TaskSummary;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserTaskServiceEJBLocal userTaskService;

    @EJB
    private RuntimeDataServiceEJBLocal runtimeDataService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String cmd = req.getParameter("cmd");
        String user = req.getParameter("user");

        if (cmd.equals("list")) {

            List<TaskSummary> taskList;
            try {
                taskList = runtimeDataService.getTasksAssignedAsPotentialOwner(user, null);
            } catch (Exception e) {
                throw new ServletException(e);
            }
            req.setAttribute("taskList", taskList);
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/task.jsp");
            dispatcher.forward(req, res);

        } else if (cmd.equals("approve")) {

            String message;
            long taskId = Long.parseLong(req.getParameter("taskId"));
            try {
                CompositeCommand compositeCommand = new CompositeCommand(new CompleteTaskCommand(taskId, user, null),
                        new StartTaskCommand(taskId, user));
                userTaskService.execute(StartupBean.DEPLOYMENT_ID, compositeCommand);
                message = "Task (id = " + taskId + ") has been completed by " + user;
                System.out.println(message);
            } catch (Exception e) {
                message = "Task operation failed. Please retry : " + e.getMessage();
                throw new ServletException(e);
            }
            req.setAttribute("message", message);
            ServletContext context = this.getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, res);

        }
    }

}