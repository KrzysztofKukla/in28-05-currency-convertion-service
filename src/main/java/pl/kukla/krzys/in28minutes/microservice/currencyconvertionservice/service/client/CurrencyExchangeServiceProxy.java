package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.web.model.CurrencyConversionDto;

/**
 * @author Krzysztof Kukla
 */
//it allows to talk to external microservice
//'name' and 'url' of the service which we are going to call ( take from spring.application.name from .properties )
@FeignClient(name = "04-currency-exchange-service", url = "localhost:8000")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/v1/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionDto retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
