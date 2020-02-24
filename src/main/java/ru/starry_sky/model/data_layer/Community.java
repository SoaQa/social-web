package ru.starry_sky.model.data_layer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Communities")
@Data
@NamedNativeQuery(  name="getUserCommunitiesID",
        query = "select community_id community_id " +
                "from users_communities " +
                "where user_id = :id",
        resultClass = CommunityID.class,
        resultSetMapping = "communitiesIDMapping")
@SqlResultSetMapping(
        name = "communitiesIDMapping",
        entities = @EntityResult(
                entityClass = CommunityID.class,
                fields = {@FieldResult(name = "communityID", column = "community_id")}))
public class Community {
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
    @ManyToMany(mappedBy = "communities", fetch = FetchType.LAZY)
    private List<User> users;
}
