package ru.starry_sky.model.data_layer.embedded_keys;

import lombok.Data;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class UsersCommunitiesPK implements Serializable {

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "community_id")
    private Long communityID;
}
