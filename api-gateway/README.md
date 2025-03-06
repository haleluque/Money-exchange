### Api-gateway

- Port: 8765
- Spring cloud gateway project responsible for routing, cross cutting concerns (security, logging etc) for the money exchange microservice ecosystem.
- Custom routes feature
- Logging filter implementation example
- Distributed tracing sending information using:
  - Micrometer - provides a vendor-neutral interface for dimensional metrics
  - OpenTelemetry - open standard for metrics, logs and traces
  - zipkin - provides metrics to the distributed tracing server

### Example URl
- http://localhost:8765/currency-exchange/from/USD/to/COP
- http://localhost:8765/currency-conversion/from/USD/to/COP/quantity/10