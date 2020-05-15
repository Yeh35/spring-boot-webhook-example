package me.sangoh.example.webhook.config;

import me.sangoh.example.webhook.controller.dto.SlackOutgointDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Configuration
public class SlackConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SlackConverterConfig.MultiValueMapToSlackOutgointDtoConverter());
    }

    public static class MultiValueMapToSlackOutgointDtoConverter implements Converter<Map, SlackOutgointDto> {
        @Override
        public SlackOutgointDto convert(Map sourceMap) {

            List<String> textList = new ArrayList<>();
            StringTokenizer textTokenizer = new StringTokenizer((String) sourceMap.get("text"), " ");
            while (textTokenizer.hasMoreElements()) {
                textList.add(textTokenizer.nextToken());
            }

            List<String> triggerWordList = new ArrayList<>();
            StringTokenizer triggerTokenizer = new StringTokenizer((String) sourceMap.get("trigger_word"), " ");
            while (triggerTokenizer.hasMoreElements()) {
                triggerWordList.add(triggerTokenizer.nextToken());
            }

            return SlackOutgointDto.builder()
                    .token((String) sourceMap.get("token"))
                    .teamId((String) sourceMap.get("team_id"))
                    .teamDomain((String) sourceMap.get("team_domain"))
                    .serviceId((String) sourceMap.get("service_id"))
                    .channelId((String) sourceMap.get("channel_id"))
                    .channelName((String) sourceMap.get("channel_name"))
                    .timestamp((String) sourceMap.get("timestamp"))
                    .userId((String) sourceMap.get("user_id"))
                    .userName((String) sourceMap.get("user_name"))
                    .text(textList)
                    .triggerWord(triggerWordList)
                    .build();
        }
    }
}
