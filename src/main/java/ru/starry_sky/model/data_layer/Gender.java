package ru.starry_sky.model.data_layer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genders")
@NoArgsConstructor
@Getter
@Setter
public class Gender {
    @Id
    private String name;
}
