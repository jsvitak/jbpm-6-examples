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
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.model.DeploymentUnit;
import org.jbpm.services.cdi.Kjar;
import org.kie.internal.runtime.cdi.BootOnLoad;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
@BootOnLoad
public class StartupBean {

    public static final String DEPLOYMENT_ID = "org.jbpm.examples:rewards:1.0";

    @Inject
    @Kjar
    DeploymentService deploymentService;

    @PostConstruct
    public void init() {
        String[] gav = DEPLOYMENT_ID.split(":");
        DeploymentUnit deploymentUnit = new KModuleDeploymentUnit(gav[0], gav[1], gav[2]);
        deploymentService.deploy(deploymentUnit);
    }

}
