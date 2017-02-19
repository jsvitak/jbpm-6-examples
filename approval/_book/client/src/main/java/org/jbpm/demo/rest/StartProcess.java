package org.jbpm.demo.rest;

import static  org.jbpm.demo.rest.Constants.*;

import java.io.InputStream;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;

public class StartProcess {
    
    

    public static void main(String[] args) throws Exception {

        System.out.println(START_PROCESS);
        
        WebClient wc = WebClient.create(START_PROCESS);
        wc.header("Authorization", authorizationHeader);
        Response resp = wc.form(new Form());
        System.out.println(IOUtils.toString((InputStream) resp.getEntity()));
        wc.close();
        
    }


}
