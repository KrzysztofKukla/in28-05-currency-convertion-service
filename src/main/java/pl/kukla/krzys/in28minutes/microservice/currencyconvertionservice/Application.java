package pl.kukla.krzys.in28minutes.microservice.currencyconvertionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient // it enables service as Eureka client / register with naming server
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
