package org.jbpm.demo.rewards.example;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;

public class Example_6_RuntimeManager {

    public static void main(String[] args) {

        RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory.get()
                .newDefaultInMemoryBuilder()
                .addAsset(ResourceFactory.newClassPathResource("rewards.bpmn"), ResourceType.BPMN2)
                .get();
        
        RuntimeManager manager = RuntimeManagerFactory.Factory.get().newSingletonRuntimeManager(environment);
        
        RuntimeEngine runtime = manager.getRuntimeEngine(EmptyContext.get());
        
        KieSession ksession = runtime.getKieSession();
        
        manager.disposeRuntimeEngine(runtime);
    }

}
