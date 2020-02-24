package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class WallCommentDTO {
    private Long messageID;
    private Long senderID;
    private String body;
}
