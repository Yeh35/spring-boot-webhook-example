package me.sangoh.example.webhook.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("slack")
public class SlackConfiguration {

    private String webHook_input_url;
    private String webHook_output_token;

}
