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

import org.jbpm.services.api.DeploymentEventListener;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ListenerSupport;
import org.jbpm.services.api.model.DeployedUnit;
import org.jbpm.services.api.model.DeploymentUnit;
import org.kie.api.runtime.manager.RuntimeManager;

import java.util.Collection;

public class RewardsDeploymentService implements DeploymentService,ListenerSupport {
    @Override
    public void deploy(DeploymentUnit unit) {

    }

    @Override
    public void undeploy(DeploymentUnit unit) {

    }

    @Override
    public RuntimeManager getRuntimeManager(String deploymentUnitId) {
        return null;
    }

    @Override
    public DeployedUnit getDeployedUnit(String deploymentUnitId) {
        return null;
    }

    @Override
    public Collection<DeployedUnit> getDeployedUnits() {
        return null;
    }

    @Override
    public void activate(String deploymentId) {

    }

    @Override
    public void deactivate(String deploymentId) {

    }

    @Override
    public boolean isDeployed(String deploymentUnitId) {
        return false;
    }

    @Override
    public void addListener(DeploymentEventListener listener) {

    }

    @Override
    public void removeListener(DeploymentEventListener listener) {

    }

    @Override
    public Collection<DeploymentEventListener> getListeners() {
        return null;
    }
}