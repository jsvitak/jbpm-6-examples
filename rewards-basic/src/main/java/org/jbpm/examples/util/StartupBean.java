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

package org.jbpm.examples.util;

import org.jbpm.kie.services.impl.KModuleDeploymentUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.ejb.api.DeploymentServiceEJBLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StartupBean {

    public static final String DEPLOYMENT_ID = "org.jbpm.examples:rewards:1.0";

    @EJB
    DeploymentServiceEJBLocal deploymentService;

    @PostConstruct
    public void init() {
        System.setProperty("org.jbpm.ht.callback", "custom");
        System.setProperty("org.jbpm.ht.custom.callback", "org.jbpm.examples.util.RewardsUserGroupCallback");
        String[] gav = DEPLOYMENT_ID.split(":");
        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
        deploymentService.deploy(deploymentUnit);
    }

}
