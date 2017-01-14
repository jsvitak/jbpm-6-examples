package org.jbpm.quickstarts.logging;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class Logging {

    public static void main(String[] args) {

        KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper.addResource(ResourceFactory.newClassPathResource("quickstarts/logging/logging.bpmn2")).build();
        KieSession ksession = kieBase.newKieSession();
        ksession.getWorkItemManager().registerWorkItemHandler("Log", new SystemOutWorkItemHandler());
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("msg", "Test Logging Message");
        ksession.startProcess("org.jbpm.quickstarts.logging", params);
    }

}
