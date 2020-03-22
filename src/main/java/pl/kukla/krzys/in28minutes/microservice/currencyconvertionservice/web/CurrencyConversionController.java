package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.service.client.CurrencyExchangeServiceProxy;
import pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.web.model.CurrencyConversionDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RestController
@RequestMapping("/v1/currency-converter")
@RequiredArgsConstructor
@Slf4j
public class CurrencyConversionController {

    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
    private final Environment environment;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionDto> convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        log.debug("calling to currency-exchange-service...");

        CurrencyConversionDto currencyConversionResponse = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        BigDecimal conversionMultiple = currencyConversionResponse.getConversionMultiple();
        CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto
            (UUID.fromString("768323bc-aa76-4e56-b076-884f9e72ab3e"), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple));
        log.debug("{}", currencyConversionResponse);
        return ResponseEntity.ok(currencyConversionDto);
    }

}
