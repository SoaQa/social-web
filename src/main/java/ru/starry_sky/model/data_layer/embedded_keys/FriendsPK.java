package ru.starry_sky.model.data_layer.embedded_keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class FriendsPK implements Serializable {

    @Column(name="user_id1")
    private Long UserID1;

    @Column(name = "user_id2")
    private Long UserID2;
}
