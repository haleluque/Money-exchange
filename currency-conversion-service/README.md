# Currency Exchange App
- Ports: 8100, 8101, 8102 etc
- Application responsible for converting one currency in another.
- It calls the Currency exchange service using rest template and FEIGN
- Distributed tracing sending information using:
  - Micrometer - provides a vendor-neutral interface for dimensional metrics
  - OpenTelemetry - open standard for metrics, logs and traces
  - zipkin - provides metrics to the distributed tracing server
  - feign micrometer - allow traces when using feign 

### Example URl
http://localhost:8100/currency-conversion/from/USD/to/COP/quantity/10
http://localhost:8100/currency-conversion/from/USD/to/COP/quantity/10/rest-template