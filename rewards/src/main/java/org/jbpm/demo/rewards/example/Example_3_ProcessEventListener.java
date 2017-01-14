package org.jbpm.demo.rewards.example;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class Example_3_ProcessEventListener {

    public static void main(String[] args) {

        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                         .addResource(ResourceFactory.newClassPathResource("sayHello.bpmn2"))
                         .build();
        
        KieSession ksession = kieBase.newKieSession();
        KieRuntimeLogger logger = KieServices.Factory.get().getLoggers().newConsoleLogger(ksession);
        ksession.startProcess("sayhello.sayHello");
        logger.close();
    }

}
