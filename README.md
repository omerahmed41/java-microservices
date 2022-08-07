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
* Design patterns (Pub-Sub, Command, Repository, Singleton).
* layer architecture (DDD).
* SpringBoot CI/CD Pipeline.
* kubernetes.

## System Architecture:
![billie](https://user-images.githubusercontent.com/15717941/183286123-59ed4669-08ef-4565-8ead-d3d44defb9cc.jpg)


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

## Calculate Invoices:
#### To achieve low latency: we crete empty invoice with Due_date based on client billing_interval everytime We are adding new client, then every time we add a new transaction to the system we process it and add it to the invoice, then we run a cron job  every day to process all Due date invoices. after invoice is processed we mark it as done and create new one with new due date.

## Design Patterns:
* Pub-Sub: I used bub-sub model along with events streaming broker rabbitmq.
* Command: I used two commands class for chargeClientCommand and createInvoiceCommand.
* Repository: Used repository pattern to decouple Domain layer from DB layer, for example we can mock the repository and use DB memory.
* Singleton: Singleton pattern is used in Spring bean.

## CI/CD:
#### Two steps: Build with tests, then Deploy.
#### I commented the part of pushing the images to DockerHub then uploading it to the cloud but, you can easily uncomment that to make it work.

## Todo:
#### the goal was to build the skeleton and base Architecture of the system, but these are Things need to be done when have more time: 
* Review and add more unit, integrations, contracts and App tests.
* Build Frontend with React.js.
* Add more App Validations.
* Focus more on documentation.
* Auth Service with Keycloak.
* REDIS.
* GRPC (support HTTP2/Websocket).

* Note make sure you have Docker installed and give it enough memory from the setting, because we have 6 services running with 4 DBs.
<img width="1440" alt="Screen Shot 2022-08-07 at 3 53 13 PM" src="https://user-images.githubusercontent.com/15717941/183289201-10746be4-af21-4c2e-8242-bf1921c6faef.png">


