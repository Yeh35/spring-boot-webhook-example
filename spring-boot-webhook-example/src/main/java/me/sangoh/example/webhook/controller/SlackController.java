package me.sangoh.example.webhook.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.sangoh.example.webhook.config.SlackConfiguration;
import me.sangoh.example.webhook.controller.dto.SlackIncomingDto;
import me.sangoh.example.webhook.controller.dto.SlackOutgointDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api/slack/v1")
@AllArgsConstructor
@Slf4j
public class SlackController {

    private ConversionService conversionService;

    private SlackConfiguration slackConfiguration;

    /**
     * Slack text : bot {action} {others}
     */
    @ResponseBody
    @RequestMapping(value = "bot", method = POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public SlackIncomingDto setServer(@RequestParam Map<String, String> paramMap) {

        // parameter를 바로 SlackOutgointDto 받는 방법은 없나.. HttpMessageConverters에서 해주는 거라던데
        log.debug("Slack Text: \"" + paramMap.get("text") + "\"");
        SlackOutgointDto slackOutgointDto = conversionService.convert(paramMap, SlackOutgointDto.class);

        if (slackConfiguration.getWebHook_output_token().equals(slackOutgointDto.getToken())) {
            throw new IllegalArgumentException("check token please");
        }

        if (slackOutgointDto.getText().size() < 2) {
            return getHelp();
        }

        String action = slackOutgointDto.getText().get(1);

        switch (slackOutgointDto.getText().get(1)) {
            case "hi" : {

                return new SlackIncomingDto.Basic().builder()
                        .text(slackOutgointDto.getUserName() + " 어서와")
                        .build();
            }

            case "bye" : {
                return new SlackIncomingDto.Basic().builder()
                        .text(slackOutgointDto.getUserName() + " 잘가")
                        .build();
            }

            default:
                return getHelp();
        }
    }

    private SlackIncomingDto.Basic getHelp() {

        String helpText = "그렇게 쓰는거 아닙니다."
                + "\n bot {action} {others}"
                + "\n {action}"
                + "\n   hi: 출근"
                + "\n   bye: 퇴근";

        return new SlackIncomingDto.Basic().builder()
                .text(helpText)
                .build();
    }
}
