# Currency Exchange App
- Ports 8000, 8001, 8002 etc
  Application responsible for getting the exchange rate of one currency in another.
- In this example this microservice has multiple artificial instances with different ports.
- This application has as well a JPA basic implementation where the data will be saved in a PostgresQL database
- Resilience4j example for retrying and circuit breaker handle.
- Distributed tracing sending information using:
  - Micrometer - provides a vendor-neutral interface for dimensional metrics
  - OpenTelemetry - open standard for metrics, logs and traces
  - zipkin - provides metrics to the distributed tracing server

### Example URl
http://localhost:8000/currency-exchange/from/USD/to/COP

## Steps to set up postgresSQL database container and microservice
### Shared steps
OPTIONAL - create a local docker network to include your containers
  ```bash 
    docker network create local_network_haleluque
  ```
### 1. Docker compose steps
Go to the parent path (cd ..)

Make sure no previous images of docker, zipkin or the microservice has been built

Generate the currency-exchange-service image with the command
  ```bash 
    mvn spring-boot:build-image
  ```

Run the following command
  ```bash 
    docker compose up -d --build
  ```

### 2. Manual steps
- Ports: 
  - Docker network: 5432
  - Host: 5433

Proceed with the following steps in order to build and run the postgresSQL database in docker:

OPTIONAL - set a back up of your local database, otherwise you can use the one added in the path: 
- 'src/main/resources/sql/db-export/moneyExchange.sql'

Go to the path 'src/main/resources/sql/db-export/' and run the command to build the docker image. (Maintain the original name)
  ```bash 
    docker build -t postgres-db:v1 .
  ```
Run the container with the following command 
  ```bash 
    docker run -d --name postgres-db --network local_network_haleluque -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=moneyExchange -p 5433:5432 postgres-db:v1
  ```
You can connect to the new postgres container using pgAdmin or other tool aiming to localhost:5433
Run the script that is in the following path: 
- 'src/main/resources/sql/insertExchangeData.sql'

Generate the currency-exchange-service image with the command  
  ```bash 
    mvn spring-boot:build-image
  ```
Run the container with the following command '
  ```bash 
    docker run -d --name currency-exchange-service --network local_network_haleluque -e DB_HOST=postgres-db -e DB_PORT=5432 -e DB_USER=postgres -e DB_PASSWORD=admin -e DB_NAME=moneyExchange -p 8000:8000 haleluque/micro-currency-exchange-service:0.0.1-SNAPSHOT
  ```