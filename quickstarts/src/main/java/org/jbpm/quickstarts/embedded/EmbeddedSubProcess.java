package org.jbpm.quickstarts.embedded;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.workflow.instance.WorkflowProcessInstance;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class EmbeddedSubProcess {

	public static void main(String[] args) {
	    KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
                .addResource(ResourceFactory.newClassPathResource("quickstarts/embedded/embeddedSubProcess.bpmn2"))
                .build();
        KieSession ksession = kieBase.newKieSession();
        
        List<String> messages = new ArrayList<String>();
        messages.add("message 1");
        messages.add("message 2");
        messages.add("message 3");
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("messages", messages); 
        
        System.out.println("Before: " + params.get("messages"));
        ProcessInstance process = ksession.startProcess("org.jbpm.quickstarts.embeddedSubProcess", params);
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) process;
        System.out.println("After: " + processInstance.getVariable("messages"));
        ksession.dispose();
	}


}
