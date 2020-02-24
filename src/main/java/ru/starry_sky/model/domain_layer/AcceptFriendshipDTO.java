package ru.starry_sky.model.domain_layer;

import lombok.Data;

@Data
public class AcceptFriendshipDTO {
    private Long requester;
    private boolean accept;
}
