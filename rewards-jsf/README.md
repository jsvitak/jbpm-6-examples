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
 - This artifact can be built in kie workbench
 - Download latest kie workbench, or download full jbpm installer
 - Run the kie workbench
 - You can use default demo organizational unit
 - Create your own git repo (TODO: or clone my example git repository for assets from github)
 - Create/Open project with groupId org.jbpm.examples, artifactId rewards, version 1.0
 - Create/Open business process rewards, you can import there the one from `src/main/resources/rewards.bpmn2`
 - Its process ID has to be org.jbpm.example.rewards (it has to match the ID in ProcessBean.java)
 - Build & Deploy this project, this will also install the artifact to your local repository and web application can load it
- Download somewhere JBoss EAP 6.1 (was tested on JBoss EAP 6.1, higher versions should work too)
- Start the application server (default datasource is ExampleDS, the same as in EAP, so the rewards-jsf example works out of the box):
 - `cd jboss-eap-6.1/bin`
 - `./standalone.sh`
- Build and deploy the example application:
 - `cd jbpm-6-examples/rewards-jsf`
 - `mvn clean package`
 - `mvn jboss-as:deploy`
- Visit [http://localhost:8080/rewards-jsf/](http://localhost:8080/rewards-jsf/) with a web browser
 - [Start Reward Process] is to start a new process
 - [John's Task] is to list John's tasks and later approve them
 - [Mary's Task] is to list Mary's tasks and later approve them

