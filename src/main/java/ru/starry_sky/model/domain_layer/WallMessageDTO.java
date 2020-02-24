package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class WallMessageDTO {
    private Long senderID;
    private Long recipientID;
    private String body;
}
