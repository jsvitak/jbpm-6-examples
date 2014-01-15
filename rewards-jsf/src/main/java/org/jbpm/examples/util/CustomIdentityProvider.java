package org.jbpm.examples.util;

import org.jbpm.kie.services.api.IdentityProvider;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collections;
import java.util.List;

// dummy CustomIdentityProvider solves:
// org.jboss.weld.exceptions.DeploymentException: WELD-001408 Unsatisfied dependencies for type [IdentityProvider]
// with qualifiers [@Default] at injection point [[field] @Inject private org.jbpm.kie.services.impl.KModuleDeploymentService.identityProvider]
@ApplicationScoped
public class CustomIdentityProvider implements IdentityProvider {

    public String getName() {
        return "dummy";
    }

    public List<String> getRoles() {

        return Collections.emptyList();
    }

}
