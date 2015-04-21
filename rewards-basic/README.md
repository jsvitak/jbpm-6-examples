rewards-basic
=============

This is an example web application for jBPM 6. It was created by forking the rewards-basic application by Toshiya 
Kobayashi:
https://github.com/tkobayas/jbpm5example/tree/master/rewards-basic

Also the project structure has changed from Java EE 5 to Java EE 6.
The further upgrade was from jBPM 6.0 core APIs to jBPM 6.2 services.

This simple example aims to provide an example usage of:
- Human tasks
- jBPM EJB services
 - Deployment service
 - Process service
 - Runtime data service
 - User task service
- Persistence configuration 
- JSP frontend
- Maven build

### Steps to run
- Make sure you have at least JDK 6 and Maven 3 installed
- Make sure you have installed org.jbpm.examples:rewards:1.0 maven artifact from [https://github
.com/jsvitak/jbpm-6-examples-assets](https://github.com/jsvitak/jbpm-6-examples-assets) into your local maven repository
- Download JBoss EAP 6.4 from [JBoss](http://www.jboss.org/products/eap/download/)
- Start the application server, for example:
```sh
cd jboss-eap-6.4.0/bin
./standalone.sh
```
- Build and deploy the example application:
```sh
cd jbpm-6-examples/rewards-basic
mvn clean package
mvn jboss-as:deploy
```
- Visit http://localhost:8080/rewards-basic/ with a web browser
 - [Start Reward Process] is to start a new process
 - [Jiri's Task] is to list Jiri's tasks and approve them
 - [Mary's Task] is to list Mary's tasks and approve them


