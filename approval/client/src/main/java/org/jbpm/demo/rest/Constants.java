package org.jbpm.demo.rest;

import org.apache.cxf.common.util.Base64Utility;

/**
 * http://localhost:8080/jbpm-console/rest-api.jsp
 * @author kylin
 *
 */
public interface Constants {

    public static final String DEPLOYMENT_ID = "org.jbpm.quickstarts:aun:2.0.1";
    
    public static final String PROCESS_ID = "org.jbpm.quickstarts.Aun";
    
    // localhost
    public static final String APP_URL = "http://localhost:8080/jbpm-console/rest";

    public static final String authorizationHeader = "Basic " + Base64Utility.encode("admin:password1!".getBytes());
    
    public static final String START_PROCESS = APP_URL + "/runtime/" + DEPLOYMENT_ID + "/process/org.jbpm.quickstarts.Aun/start" ;
    
    public static final String GET_PROCESS_INSTANCES = APP_URL + "/query/runtime/process";
    
    public static final String GET_TASKS = APP_URL + "/query/runtime/task";
    
    public static final String TASK_ID = "5"; // this depend on  GET_TASKS
    public static final String TASK_RELEASE = APP_URL + "/task/" + TASK_ID + "/release";
    public static final String TASK_CLIAM = APP_URL + "/task/" + TASK_ID + "/claim";
    public static final String TASK_COMPLETE = APP_URL + "/task/" + TASK_ID + "/complete";
}
