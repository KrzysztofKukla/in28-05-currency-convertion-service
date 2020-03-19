package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author Krzysztof Kukla
 */
@Configuration
@EnableFeignClients("pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice")
public class FeignConfiguration {
}
