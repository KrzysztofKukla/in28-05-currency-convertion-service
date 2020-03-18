package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.web.model.CurrencyConversionDto;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@RestController
@RequestMapping("/v1/currency-converter")
@Slf4j
public class CurrencyConversionController {

    private static final String CURRENCY_EXCHANGE_SERVICE_URL = "http://localhost:8000/v1/currency-exchange";

    private final Environment environment;

    private final RestTemplate restTemplate;

    public CurrencyConversionController(Environment environment, RestTemplateBuilder restTemplateBuilder) {
        this.environment = environment;
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversionDto> convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        log.debug("server.port=" + environment.getProperty("server.port"));
        ResponseEntity<CurrencyConversionDto> currencyExchangeResponse =
            restTemplate.getForEntity(CURRENCY_EXCHANGE_SERVICE_URL + "/from/{from}/to/{to}", CurrencyConversionDto.class, from, to);
        CurrencyConversionDto responseBody = currencyExchangeResponse.getBody();

        BigDecimal conversionMultiple = responseBody.getConversionMultiple();
        CurrencyConversionDto currencyConversionDto = new CurrencyConversionDto
            (UUID.fromString("768323bc-aa76-4e56-b076-884f9e72ab3e"), from, to, conversionMultiple, quantity, quantity.multiply(conversionMultiple));
        return ResponseEntity.ok(currencyConversionDto);
    }

}
