# Java Spring-boot- Microservices
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

## Docs:
#### I used OpenAPI with swagger for API docs, also  followed Domain driven design with services Layer architecture to make it easy to understand the code
#### Lastly the Naming of Classes, methods and objects is meaningful.
to check the API docs on localhost:
* Client Service http://localhost:8083
* transaction Service http://localhost
* invoice Service http://localhost:8082
#### When use frontend like React.js or any client we can the advantage of the API gateway.



## Communications: 
#### For Async communications I used rabbitmq, and for sync I used normal http calls later on grpc will be good use, also we can use REDIS as improvement.

## CI/CD:
#### Two steps: Build with tests, then Deploy.
#### I commented the part of pushing the images to DockerHub then uploading it to the cloud but you can easily uncomment that to make it work.

## Todo:
#### the goal was to build the skeleton and base Architecture of the system, but these are Things need to be done when have more time: 
* Review and add more unit, integrations, contracts and App tests.
* Build Frontend with React.js.
* Add more App Validations.
* Focus more on documentation.
* Auth Service with Keycloak.
* REDIS.
* GRPC (support HTTP2/websocket).