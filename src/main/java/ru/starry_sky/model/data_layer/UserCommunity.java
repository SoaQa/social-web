package ru.starry_sky.model.data_layer;

import lombok.*;
import ru.starry_sky.model.data_layer.embedded_keys.UsersCommunitiesPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users_communities")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCommunity {
    @EmbeddedId
    private UsersCommunitiesPK id;
}
