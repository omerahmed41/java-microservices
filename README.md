# java-microservices
Java Spring-boot Micro-services blueprint

Features:
* Spring-boot fresh build
* docker with Docker compose.
* open API and swagger.
* makefile.
* Logs slf4j.
* message broker - rabbitmq.
* auth service- keycloak ( todo).
* Service registry.
* API-gateway.
* Design patterns (Pub-Sub, Command, Repository).
* layer architecture (DDD).
* SpringBoot CI/CD Pipeline.
* kubernetes.



## Setup: 
### run:
* make build
* make run
* to reload the Service use make reload


## RabbitMq Dashboard:
* URL: http://localhost:15672
* username: guest
* password: guest
<img width="1440" alt="rabbitmq" src="https://user-images.githubusercontent.com/15717941/183268850-8a03311f-9409-4f19-ba30-10be962da86d.png">

## Eureka Service:
#### to see all Instances currently registered with Eureka
* URL: http://localhost:8761
<img width="1440" alt="Services" src="https://user-images.githubusercontent.com/15717941/183268815-6599e30c-66ed-4fdd-ae61-09d864ba6a4e.png">

## APIs Docs:
#### we used OpenAPI wiith swagger
for localhost use
* Client Servic http://localhost:8083
* transaction Servic http://localhost
* invoice Servic http://localhost:8082
#### When use frontend like React.js or any client we can tahe davantge of the API gateway.

