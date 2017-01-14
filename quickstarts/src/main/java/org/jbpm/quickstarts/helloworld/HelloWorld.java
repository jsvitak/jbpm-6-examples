package org.jbpm.quickstarts.helloworld;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;

public class HelloWorld {

	public static void main(String[] args) {
		KieHelper kieHelper = new KieHelper();
		KieBase kieBase = kieHelper.addResource(ResourceFactory.newClassPathResource("quickstarts/helloworld/HelloWorld.bpmn2")).build();
		KieSession ksession = kieBase.newKieSession();
		ksession.startProcess("org.jbpm.quickstarts.HelloWorld");
	}

}
