package org.jbpm.quickstarts.multiple;

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

public class MultipleNodeInstanceProcess {

	public static void main(String[] args) {
	    KieHelper kieHelper = new KieHelper();
        KieBase kieBase = kieHelper
//                .addResource(ResourceFactory.newClassPathResource("quickstarts/multipleinstance/multipleNodeInstanceProcess.bpmn"))
                .addResource(ResourceFactory.newClassPathResource("quickstarts/multipleinstance/multipleNodeInstanceProcess.bpmn2"))
                .build();
        KieSession ksession = kieBase.newKieSession();
        
        List<Number> numbers = new ArrayList<Number>();
        numbers.add(2);
        numbers.add(4);
        numbers.add(56);
        numbers.add(7);
        numbers.add(10);
        numbers.add(13);
        
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("numberList", numbers);
        parameters.put("oddList", new ArrayList<Number>());
        
        ProcessInstance process = ksession.startProcess("org.jbpm.quickstarts.multipleNodeInstanceProcess",parameters);
        WorkflowProcessInstance processInstance = (WorkflowProcessInstance) process;
        System.out.println("Odd List: " + processInstance.getVariable("oddList"));
	}

	

}
