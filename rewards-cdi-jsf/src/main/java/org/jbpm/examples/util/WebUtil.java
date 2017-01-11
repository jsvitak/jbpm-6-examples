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

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import java.util.logging.Logger;

@RequestScoped
public class WebUtil {

    private FacesContext facesContext;

    @PostConstruct
    private void init() {
        facesContext = FacesContext.getCurrentInstance();
    }

    @Produces
    public FacesContext produceFacesContext() {
        return facesContext;
    }

    @Produces
    public Logger produceLog(InjectionPoint injectionPoint) {
            return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
