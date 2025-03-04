package com.haleluque.currency_exchange_service.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

    private Logger logger =
            LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api/retry")
    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Sample api call received");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url",
					String.class);
		return forEntity.getBody();
    }

    @GetMapping("/sample-api/circuitBreaker")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name="default")
    @Bulkhead(name="sample-api") //concurrent calls
    public String sampleApiCircuitBreaker() {
        logger.info("Sample api circuit breaker call received");
        return "sample-api";
    }

    /**
     * Fallback method receives a throwable as parameter
     * you can have several fallback method per exception
     * @param ex
     * @return
     */
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}