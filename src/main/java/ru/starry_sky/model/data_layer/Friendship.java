package ru.starry_sky.model.data_layer;

import lombok.*;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedNativeQueries({
        @NamedNativeQuery(  name="getUserFriendsID",
                            query = "select requester_id user_id " +
                                    "from friendship " +
                                    "where accepted " +
                                    "and friend = :id " +
                                    "union " +
                                    "select friend user_id " +
                                    "from friendship " +
                                    "where accepted " +
                                    "and requester = :id ",
                            resultClass = Long.class),
        @NamedNativeQuery(  name = "getUnAcceptedRequesters",
                            query = "select requester_id " +
                                    "from friendship " +
                                    "where not accepted " +
                                    "and friend = :id",
                            resultClass = Long.class)
})
public class Friendship {

    @EmbeddedId
    private FriendsPK id;

    @Column(name = "accept")
    private boolean accept;

}
