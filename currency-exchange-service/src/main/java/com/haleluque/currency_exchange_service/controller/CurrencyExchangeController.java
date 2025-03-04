package com.haleluque.currency_exchange_service.controller;

import com.haleluque.currency_exchange_service.model.CurrencyExchange;
import com.haleluque.currency_exchange_service.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    @Autowired
    private CurrencyExchangeRepository repository;

    @Autowired
    private Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to) {
        CurrencyExchange currencyExchange
                = repository.findByFromAndTo(from, to);

        logger.info("retrievedExchangeValue called with {} to {}", from, to);

        if (currencyExchange == null) {
            throw new RuntimeException
                    ("Unable to Find data for " + from + " to " + to);
        }

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}