package org.jbpm.demo.rewards.example;

import org.kie.api.KieBase;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class Example_1_KieBase {

    public static void main(String[] args) {

        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                         .addResource(ResourceFactory.newClassPathResource("sayHello.bpmn2"))
                         .build();
    }

}
