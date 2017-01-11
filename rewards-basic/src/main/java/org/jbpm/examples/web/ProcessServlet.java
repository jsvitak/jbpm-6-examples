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
import org.jbpm.services.ejb.api.ProcessServiceEJBLocal;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ProcessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private ProcessServiceEJBLocal processService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        String recipient = req.getParameter("recipient");

        long processInstanceId = -1;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("recipient", recipient);
            processInstanceId = processService.startProcess(StartupBean.DEPLOYMENT_ID,
                    "org.jbpm.examples.rewards", params);
            System.out.println("Process instance " + processInstanceId + " has been successfully started.");
        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.setAttribute("message", "process instance (id = "
                + processInstanceId + ") has been started.");

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context
                .getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, res);
    }
}