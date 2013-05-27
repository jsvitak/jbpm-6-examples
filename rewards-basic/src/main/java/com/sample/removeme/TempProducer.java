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
