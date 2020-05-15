package me.sangoh.example.webhook.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SlackIncomingDto {

    @Getter
    @NoArgsConstructor
    public static class Basic extends SlackIncomingDto {
        private String text;

        @Builder
        public Basic(String text) {
            this.text = text;
        }
    }

    /* 그외에 다양한 방법이 있지만 예제임으로 Pass */
}
