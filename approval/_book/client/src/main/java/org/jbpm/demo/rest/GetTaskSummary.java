package org.jbpm.demo.rest;

import static org.jbpm.demo.rest.Constants.GET_TASKS;
import static org.jbpm.demo.rest.Constants.authorizationHeader;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class GetTaskSummary {

    public static void main(String[] args) throws IOException {

        System.out.println(GET_TASKS);
        
        WebClient wc = WebClient.create(GET_TASKS);
        wc.header("Authorization", authorizationHeader);
        Response resp = wc.get();
        System.out.println(IOUtils.toString((InputStream) resp.getEntity()));
        wc.close();
    }

}
