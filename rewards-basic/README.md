rewards-basic
=============

This is an example web application for jBPM 6. It was created by forking the rewards-basic application by Toshiya Kobayashi:
https://github.com/tkobayas/jbpm5example/tree/master/rewards-basic

Also the project structure has changed from Java EE 5 to Java EE 6.

This simple example aims to provide an example usage of:
- Human tasks
- Persistence
- Transactions
- Singleton session manager
- Context dependency injection
- Maven

This application uses single session. Other session strategies (session managers) can be demonstrated later. So far the example supports only dependencies from jBPM 6 as community project.


### Steps to run
- Download somewhere JBoss EAP (was tested on JBoss EAP 6.1, other versions should work too)
 - Configure there a datasource with JNDI name java:jboss/datasources/jbpmDS
 - You can use any supported database, persistence.xml is preconfigured to use PostgreSQL, but you can change that easily
 - Start the application server
- cd jbpm-6-examples/rewards-basic
- mvn clean package
- mvn jboss-as:deploy
- Visit http://localhost:8080/rewards-basic/ with a web browser
 - [Start Reward Process] is to start a new process
 - [John's Task] is to list John's tasks and approve them
 - [Mary's Task] is to list Mary's tasks and approve them


### TODO: check this issue with version 6:
- reward-basic.jmx is a jmeter test plan for this application.
 - You may see PermissionDeniedException or OptimisticLockException under load. It means that a user started a task which is already completed. It's expected because this test plan may cause concurrent accesses to the same task with the same user. (It may happen in real use cases)
 
