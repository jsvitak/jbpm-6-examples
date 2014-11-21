rewards-jsf
=============

This simple Java Server Faces example aims to provide an example usage of:
- Human tasks including data passing
- JPA persistence
- User transactions
- Dynamic deploy of kjar artifact containing a business process
- Context dependency injection
- Singleton session manager
- Java server faces
- Maven build

### Steps to run
- Make sure you have at least Java 6 and Maven 3 installed
- Make sure you have an artifact with GAV org.jbpm.examples:rewards:1.0 installed in your local maven repository (`~/.m2/repository`)
 - Check this repo, which provides instructions how to install this artifact [https://github
 .com/jsvitak/jbpm-6-examples-assets](https://github.com/jsvitak/jbpm-6-examples-assets)
- Download somewhere JBoss EAP 6.1 or 6.2 (BPMS 6.0.3 is based on EAP 6.1, EAP 6.3 will not work)
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

