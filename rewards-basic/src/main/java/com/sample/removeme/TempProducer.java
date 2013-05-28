/**
 * Copyright 2013 JBoss Inc
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

package com.sample.removeme;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.jbpm.services.task.wih.RuntimeFinder;
import org.kie.commons.io.IOService;
import org.kie.commons.io.impl.IOServiceNio2WrapperImpl;

/**
 * This is a temp producer that will be removed after fixing
 * some of the dependencies in jbpm that should be optional
 */
@ApplicationScoped
public class TempProducer {


    // temp hack as this needs to be changed in runtime manager to be optional
    // as it is only required when multiple runtime manager are used in one app
    @Produces
    public RuntimeFinder getFinder() {
        return new RuntimeFinder() {
            @Override public String findName(long l) {
                return null;
            }
        };
    }
    // this needs to be fixed in jbpm shared as it should be optional when using services
    @Produces
    @Named("ioStrategy")
    public IOService createIOService() {
        return new IOServiceNio2WrapperImpl();
    }
}
