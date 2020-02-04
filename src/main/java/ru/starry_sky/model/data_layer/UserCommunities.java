package ru.starry_sky.model.data_layer;

import lombok.Getter;
import lombok.Setter;
import ru.starry_sky.model.data_layer.embedded_keys.UsersCommunitiesPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Setter
@Getter
public class UserCommunities {
    public UserCommunities() {
    }

    @EmbeddedId
    private UsersCommunitiesPK id;
}
