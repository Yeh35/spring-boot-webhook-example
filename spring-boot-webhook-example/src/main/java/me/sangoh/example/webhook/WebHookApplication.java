package me.sangoh.example.webhook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebHookApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebHookApplication.class);
        app.run();
    }

}
