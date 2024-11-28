Playground for Refactoring, Redesign and Legacy Code workshops/trainings.

1. `mvn clean install`
2. `mvn sonar:sonar 
   -Dsonar.host.url=http://localhost:9000
   -Dsonar.login=<YOUR OWN TOKEN>
   -Dsonar.projectKey=task-manager` 
3. `mvn sonar:sonar
   -Dsonar.host.url=http://localhost:9000
   -Dsonar.login=<YOUR OWN TOKEN>
   -Dsonar.projectKey=task-manager
   -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco/jacoco-ut/jacoco.xml 
   -Dsonar.junit.reportPaths=target/surefire-reports  
   -Dsonar.java.pmd.reportPaths=target/pmd.xml 
   -Dsonar.java.checkstyle.reportPaths=target/checkstyle-result.xml`



