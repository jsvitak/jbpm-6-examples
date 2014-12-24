package org.jbpm.quickstarts.multipleinstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class MultipleNodeInstanceProcessStart {

	public static final void main(String[] args) {
		try {
			KnowledgeBase kbase = readKnowledgeBase();
			StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
			
			List<Person> list = new ArrayList<Person>();
			for(int i = 1 ; i < 6 ; i ++){
				list.add(new Person("1000" + i));
			}
	        Map<String, Object> param = new HashMap<String, Object>();
	        param.put("list", list);
	        param.put("localVar", new Person("00000"));
	        ksession.startProcess("org.jbpm.quickstarts.multiplenodeinstanceprocess", param);

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	private static KnowledgeBase readKnowledgeBase() throws Exception {
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newClassPathResource("multipleinstance/multipleNodeInstanceProcess.bpmn"), ResourceType.BPMN2);
		return kbuilder.newKnowledgeBase();
	}
}
