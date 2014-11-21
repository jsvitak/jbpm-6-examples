package org.jbpm.examples.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

public class RewardsRequestScopedProducer {

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
            return Logger.getLogger(injectionPoint.getMember().getDeclaringClass()
                    .getName());
    }
}
