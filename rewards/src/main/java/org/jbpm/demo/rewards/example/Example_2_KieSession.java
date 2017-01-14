package org.jbpm.demo.rewards.example;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class Example_2_KieSession {

    public static void main(String[] args) {
        
        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                         .addResource(ResourceFactory.newClassPathResource("sayHello.bpmn2"))
                         .build();
        
        KieSession ksession = kieBase.newKieSession();
        ProcessInstance processInstance = ksession.startProcess("sayhello.sayHello");
    }

}
