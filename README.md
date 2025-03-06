# Money Exchange Microservices Exercise

Java Spring Boot application that simulates a money exchange microservice architecture ecosystem.

## Main Features

The exercise is compound by the folling:

### Currency Exchange Microservice

- Ports 8000, 8001, 8002 etc
- Application responsible for getting the exchange rate of one currency in another.
- In this example this microservice has multiple artificial instances with different ports.
- This application has as well a JPA basic implementation where the data will be saved in a PostgresQL database
- Resilience4j example for retrying and circuit breaker handle.
- Distributed tracing sending information using:
  - Micrometer - provides a vendor-neutral interface for dimensional metrics
  - OpenTelemetry - open standard for metrics, logs and traces
  - zipkin - provides metrics to the distributed tracing server

### Currency Conversion Microservice

- Ports: 8100, 8101, 8102 etc
- Application responsible for converting one currency in another.
- It calls the Currency exchange service using rest template and FEIGN. However the load balancing is done with OpenFeign and Eureka using a proxy.

### Netflix Eureka Naming Server

- Port: 8761
- Project that runs as the Eureka Server, implementing Spring Cloud Eureka, to handle the service registry and load balancing.

### Api-gateway

- Port: 8765
- Spring cloud gateway project responsible for routing, cross cutting concerns (security, logging etc) for the microservice ecosystem.
- Custom routes feature
- Logging filter implementation example

### Distributed Tracing Server / Zipkin

- Port: 9411
- Distributed tracing example using zipkin, run "docker run -d -p 9411:9411 openzipkin/zipkin:latest" to pull the image and run it
- go to: http://localhost:9411/zipkin/
