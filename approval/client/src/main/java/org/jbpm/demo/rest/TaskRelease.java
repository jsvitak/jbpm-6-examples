package org.jbpm.demo.rest;

import static org.jbpm.demo.rest.Constants.TASK_RELEASE;
import static org.jbpm.demo.rest.Constants.authorizationHeader;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class TaskRelease {

    public static void main(String[] args) throws IOException {


        System.out.println(TASK_RELEASE);
        
        WebClient wc = WebClient.create(TASK_RELEASE);
        wc.header("Authorization", authorizationHeader);
        Response resp = wc.form(new Form());
        System.out.println(IOUtils.toString((InputStream) resp.getEntity()));
        wc.close();
    }

}
