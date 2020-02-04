package ru.starry_sky.model.data_layer;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Communities {
    public Communities() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(name = "community_name")
    private String communityName;

    @Column(name = "age_limit")
    private int ageLimit;
}
