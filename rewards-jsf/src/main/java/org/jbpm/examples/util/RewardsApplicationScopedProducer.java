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

package org.jbpm.examples.util;

import org.jbpm.runtime.manager.impl.cdi.InjectableRegisterableItemsFactory;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.task.UserGroupCallback;
import org.kie.internal.runtime.manager.cdi.qualifier.PerProcessInstance;
import org.kie.internal.runtime.manager.cdi.qualifier.PerRequest;
import org.kie.internal.runtime.manager.cdi.qualifier.Singleton;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

@ApplicationScoped
public class RewardsApplicationScopedProducer {

    @Inject
    private InjectableRegisterableItemsFactory factory;

    @Inject
    private UserGroupCallback usergroupCallback;

    @PersistenceUnit(unitName = "org.jbpm.examples.rewards-jsf")
    private EntityManagerFactory emf;

    @Produces
    public EntityManagerFactory produceEntityManagerFactory() {
        if (this.emf == null) {
            this.emf = Persistence
                    .createEntityManagerFactory("org.jbpm.examples.rewards-jsf");
        }
        return this.emf;
    }

    @Produces
    @Singleton
    @PerProcessInstance
    @PerRequest
    public RuntimeEnvironment produceEnvironment(EntityManagerFactory emf) {
        KieServices kieServices = KieServices.Factory.get();
        ReleaseId releaseId = kieServices.newReleaseId( "org.jbpm.examples", "rewards", "1.0" );
        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultBuilder(releaseId)
                .entityManagerFactory(emf)
                .userGroupCallback(usergroupCallback)
                .registerableItemsFactory(factory)
                .get();
        return environment;
    }

    @Produces
    public UserGroupCallback produceUserGroupCallback() {
        return new RewardsUserGroupCallback();
    }

}
