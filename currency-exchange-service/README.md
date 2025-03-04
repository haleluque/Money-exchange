# Currency Exchange App
- Ports 8000, 8001, 8002 etc
Application responsible for getting the exchange rate of one currency in another.
- In this example this microservice has multiple artificial instances with different ports.
- This application has as well a JPA basic implementation where the data will be saved in a PostgresQL database
- Resilience4j example for retrying and circuit breaker handle.

### Example URl
http://localhost:8000/currency-exchange/from/USD/to/COP