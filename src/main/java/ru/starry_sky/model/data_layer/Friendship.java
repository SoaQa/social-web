package ru.starry_sky.model.data_layer;

import lombok.*;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Friendship {

    @EmbeddedId
    private FriendsPK id;

    @Column(name = "accept")
    private boolean accept;

}
