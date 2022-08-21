package jmu.lsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EcForeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcForeignApplication.class, args);
    }

}
