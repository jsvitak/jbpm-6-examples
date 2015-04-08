rewards-cdi-jsf
===============

This simple rewards-cdi-jsf example web application is a modification of rewards-basic application
to demonstrate jBPM 6.2 services in combination with CDI and JSF frameworks, in particular: 
- Human tasks including data passing and forms
- jBPM CDI services
 - Deployment service
 - Process service
 - Runtime data service
 - User task service
- Persistence configuration
- Context and Dependency Injection beans 
- Java Server Faces frontend
- Maven build

### Steps to run
- Make sure you have at least JDK 6 and Maven 3 installed
- Make sure you have installed org.jbpm.examples:rewards:1.0 maven artifact from [https://github
.com/jsvitak/jbpm-6-examples-assets](https://github.com/jsvitak/jbpm-6-examples-assets) into your local maven repository
- Download JBoss EAP 6.3 from [JBoss](http://www.jboss.org/products/eap/download/)
- Start the application server:
```sh
cd jboss-eap-6.3.0/bin
./standalone.sh
```
- Build and deploy the example application:
```sh
cd jbpm-6-examples/rewards-cdi-jsf
mvn clean package
mvn jboss-as:deploy
```
- Visit [http://localhost:8080/rewards-jsf/](http://localhost:8080/rewards-cdi-jsf/) with a web browser
 - [Start Reward Process] is to start a new process
 - [Jiri's Task] is to list Jiri's tasks and approve them
 - [Mary's Task] is to list Mary's tasks and approve them

