rewards-jsf
=============

This simple JSF 2.1 example aims to provide an example usage of:
- Human tasks
- Persistence
- Transactions
- Singleton session manager
- Context dependency injection
- Maven

### Steps to run
- Make sure you have at least Java 6 and Maven 3 installed
- Download somewhere JBoss EAP 6.1 (was tested on JBoss EAP 6.1, other versions should work too)
- Start the application server (default datasource is ExampleDS, the same as in EAP, so the example works out of the box):
 - cd jboss-eap-6.1/bin
 - ./standalone.sh
- Build and deploy the example application:
 - cd jbpm-6-examples/rewards-jsf
 - mvn clean package
 - mvn jboss-as:deploy
- Visit http://localhost:8080/rewards-jsf/ with a web browser
 - [Start Reward Process] is to start a new process
 - [John's Task] is to list John's tasks and later approve them
 - [Mary's Task] is to list Mary's tasks and later approve them

