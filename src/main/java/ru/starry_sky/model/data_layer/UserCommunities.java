package ru.starry_sky.model.data_layer;

import lombok.*;
import ru.starry_sky.model.data_layer.embedded_keys.UsersCommunitiesPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCommunities {
    @EmbeddedId
    private UsersCommunitiesPK id;
}
