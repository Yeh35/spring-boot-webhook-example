package me.sangoh.example.webhook.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor @Builder
public class SlackOutgointDto {

    private final String token;
    private final String teamId;
    private final String teamDomain;
    private final String serviceId;
    private final String channelId;
    private final String channelName;
    private final String timestamp;
    private final String userId;
    private final String userName;
    private final List<String> text;
    private final List<String> triggerWord;

}
