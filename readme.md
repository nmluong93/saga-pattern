See: https://mobisoftinfotech.com/resources/blog/microservices/saga-pattern-spring-boot-rabbitmq-tutorial


How-to start the project: 

* Firstly, we need the RapidMQ running on docker: 
  * docker run -d --hostname rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
* Then run ./gradlew bootRun in both project locally.