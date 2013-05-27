package com.sample.util;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.runtime.manager.api.WorkItemHandlerProducer;
import org.kie.api.runtime.process.WorkItemHandler;

public class SimpleWorkItemHandlerProducer implements WorkItemHandlerProducer {

    @Override
    public Map<String, WorkItemHandler> getWorkItemHandlers(String s, Map<String, Object> stringObjectMap) {
        // add any WorkItemHandlers that should be registered on the session
        return new HashMap<String, WorkItemHandler>();
    }
}
