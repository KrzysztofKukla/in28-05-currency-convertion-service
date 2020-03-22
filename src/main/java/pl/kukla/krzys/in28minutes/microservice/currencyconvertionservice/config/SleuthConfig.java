package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Krzysztof Kukla
 */
@Configuration
public class SleuthConfig {

    @Bean
    public Sampler defaultSampler() {
        //this allows to trace all request
        return Sampler.ALWAYS_SAMPLE;
    }

}
