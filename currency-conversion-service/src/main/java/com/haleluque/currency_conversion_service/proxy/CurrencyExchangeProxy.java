package com.haleluque.currency_conversion_service.proxy;

import com.haleluque.currency_conversion_service.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//It is common to use the same 'spring.application.name' we want to call
//@FeignClient(name = "currency-exchange", url = "localhost:8000")

//Eureka will pick up the service name and will do the load balancing between instances automatically
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);
}
