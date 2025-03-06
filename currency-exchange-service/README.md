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