package org.jbpm.quickstarts.rule;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class RuleTask {

    public static void main(String[] args) {

        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                .addResource(ResourceFactory.newClassPathResource("quickstarts/rule/ruletaskprocess.bpmn2"))
                .addResource(ResourceFactory.newClassPathResource("quickstarts/rule/ruletaskprocess-rule.drl"))
                .addResource(ResourceFactory.newClassPathResource("quickstarts/rule/ruletaskprocess-rule2.drl"))
                .build();
        KieSession ksession = kieBase.newKieSession();
        ksession.startProcess("org.jbpm.quickstarts.ruletaskprocess");
        ksession.dispose();
    }

}
