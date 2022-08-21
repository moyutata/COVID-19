package jmu.lsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcNationApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcNationApplication.class, args);
    }
}
