rewards-jsf
=============

This simple Java Server Faces example aims to provide an example usage of:
- Human tasks including data passing
- JPA persistence
- User transactions
- Dynamic deploy of kjar artifact containing a business process
- Context dependency injection
- PerProcessInstance session manager
- Java server faces
- Maven build

### Steps to run
- Make sure you have at least Java 6 and Maven 3 installed
- Download JBoss EAP 6.1 or 6.2 from [JBoss](http://www.jboss.org/products/eap/download/) (EAP 6.3 will not work for 
the jbpm version used here)
- Start the application server (default datasource is ExampleDS, the same as in EAP, so the rewards-jsf example works out of the box):
 - `cd jboss-eap-6.1/bin`
 - `./standalone.sh`
- Build and deploy the example application:
 - `cd jbpm-6-examples/rewards-jsf`
 - `mvn clean package`
 - `mvn jboss-as:deploy`
- Visit [http://localhost:8080/rewards-jsf/](http://localhost:8080/rewards-jsf/) with a web browser
 - [Start Reward Process] is to start a new process
 - [Jiri's Task] is to list Jiri's tasks and later approve them
 - [Mary's Task] is to list Mary's tasks and later approve them
 
### Optional - load assets from an artifact jar
- Make sure you have an artifact with GAV org.jbpm.examples:rewards:1.0 installed in your local maven repository (`~/.m2/repository`)
 - Check this repo, which provides instructions how to install this artifact [https://github
 .com/jsvitak/jbpm-6-examples-assets](https://github.com/jsvitak/jbpm-6-examples-assets)
- Update jbpm-6-examples/rewards-jsf/src/main/java/org/jbpm/examples/util/RewardsApplicationScopedProducer.java class to
load the jar artifact instead of bundled rewards.bpmn2 process model (code is prepared and commented)
