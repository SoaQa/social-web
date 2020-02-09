package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
public class Communities {
    @Id
    @SequenceGenerator(name = "communitiesSequence", sequenceName = "communities_seq_pk", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communitiesSequence")
    @Column(name = "community_id")
    private Long id;

    @Column(name = "community_name")
    private String communityName;

    @Column(name = "age_limit")
    private int ageLimit;

    @JsonBackReference
    @ManyToMany(mappedBy = "communities")
    private List<User> users;
}
