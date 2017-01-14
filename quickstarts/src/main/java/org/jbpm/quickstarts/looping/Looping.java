package org.jbpm.quickstarts.looping;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class Looping {

    public static void main(String[] args) {

        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper.addResource(ResourceFactory.newClassPathResource("quickstarts/looping/looping.bpmn2")).build();
        KieSession ksession = kieBase.newKieSession();
        ksession.startProcess("org.jbpm.quickstarts.looping");
    }

}
