package me.sangoh.example.webhook;


import lombok.AllArgsConstructor;
import me.sangoh.example.webhook.Manager.SlackSenderManager;
import me.sangoh.example.webhook.controller.dto.SlackIncomingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class BootRunner implements ApplicationRunner {

    private SlackSenderManager senderManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String helpText = "I'm Coming";

        senderManager.send(new SlackIncomingDto.Basic().builder()
                .text(helpText)
                .build());
    }
}
