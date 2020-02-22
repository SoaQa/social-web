package ru.starry_sky.model.data_layer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.starry_sky.model.data_layer.embedded_keys.UserRolesPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRole {
    @EmbeddedId
    private UserRolesPK id;

}
