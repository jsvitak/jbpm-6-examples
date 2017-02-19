package org.jbpm.demo.rest;

import static org.jbpm.demo.rest.Constants.TASK_COMPLETE;
import static org.jbpm.demo.rest.Constants.authorizationHeader;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class TaskComplete {

    public static void main(String[] args) throws IOException {

        System.out.println(TASK_COMPLETE);
        
        WebClient wc = WebClient.create(TASK_COMPLETE);
        wc.header("Authorization", authorizationHeader);
        Form form = new Form();
        form.param("_user", "User1");
        Response resp = wc.form(form);
        System.out.println(IOUtils.toString((InputStream) resp.getEntity()));
        wc.close();
    }

}
