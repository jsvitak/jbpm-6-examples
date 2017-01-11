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

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.jbpm.examples.util.StartupBean;
import org.jbpm.services.api.ProcessService;

@Model
public class ProcessBean {

    @Inject
    ProcessService processService;

    @Inject
    FacesContext facesContext;

    @Inject
    Logger logger;

    private String recipient;
    private int reward = 200;

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public String startProcess() {
        String message;
        long processInstanceId = -1;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("recipient", recipient);
            params.put("reward", reward);
            processInstanceId = processService.startProcess(StartupBean.DEPLOYMENT_ID,
                    "org.jbpm.examples.rewards", params);
            message = "Process instance " + processInstanceId + " has been successfully started." ;
            logger.info(message);
        } catch (Exception e) {
            message = "Unable to start the business process.";
            logger.log(Level.SEVERE, message, e);
        }
        facesContext.getExternalContext().getFlash()
                .put("msg", message);
        return "index.xhtml?faces-redirect=true";
    }
}
