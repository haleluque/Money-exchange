# Money Exchange Microservices Exercise
Java Spring Boot application that simulates a money exchange microservice architecture ecosystem.

## Main Features
The exercise is compound by the folling:

### Currency Exchange Microservice
- Ports 8000, 8001, 8002 etc
- Application responsible for getting the exchange rate of one currency in another.
- In this example this microservice has multiple artificial instances with different ports.
- This application has as well a JPA basic implementation where the data will be saved in a PostgresQL database

### Currency Conversion Microservice
- Ports: 8100, 8101, 8102 etc
- Application responsible for converting one currency in another.
- It calls the Currency exchange service using rest template and FEIGN. However the load balancing is done with OpenFeign and Eureka using a proxy. 

### Netflix Eureka Naming Server
- Port: 8761
- Project that runs as the Eureka Server, implementing Spring Cloud Eureka, to handle the service registry and load balancing.
