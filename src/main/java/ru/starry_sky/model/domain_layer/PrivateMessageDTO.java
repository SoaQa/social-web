package ru.starry_sky.model.domain_layer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PrivateMessageDTO {
    @NotNull
    private Long senderID;
    @NotNull
    private Long recipientID;
    private String messageBody;
}
