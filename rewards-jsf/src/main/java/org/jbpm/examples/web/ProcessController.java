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

import org.jbpm.examples.ejb.ProcessBean;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
public class ProcessController {

    @EJB
    ProcessBean processBean;

    @Inject
    FacesContext facesContext;

    @Inject
    Logger logger;

    private String recipient;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public String startProcess() {
        FacesMessage fm;
        try {
            long processInstanceId = processBean.startProcess(recipient);
            String message = "Process instance with ID " + processInstanceId + " has been successfully started." ;
            logger.info(message);
            fm = new FacesMessage(FacesMessage.SEVERITY_INFO, message, "Success!");
        } catch (Exception e) {
            String message = "Unable to start the business process.";
            logger.log(Level.SEVERE, message, e);
            fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, "Fail!");
        }
        facesContext.addMessage(null, fm);
        return "index.xhtml?faces-redirect=true";
    }
}
