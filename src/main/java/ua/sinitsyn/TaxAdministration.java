package ua.sinitsyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxAdministration {
    public static void main(String[] args) {
        SpringApplication application =
                new SpringApplication(TaxAdministration.class);
        application.run(args);
    }
}
