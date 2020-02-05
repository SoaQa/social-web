package ru.starry_sky.model.data_layer;

import lombok.Data;
import ru.starry_sky.model.data_layer.embedded_keys.UsersCommunitiesPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Data
public class UserCommunities {
    @EmbeddedId
    private UsersCommunitiesPK id;
}
