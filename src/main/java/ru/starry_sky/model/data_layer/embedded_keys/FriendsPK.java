package ru.starry_sky.model.data_layer.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FriendsPK implements Serializable {

    @Column(name="requester")
    private Long requester;

    @Column(name = "friend")
    private Long friend;
}
