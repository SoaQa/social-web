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
                            query = "select requester user_id " +
                                    "from friendship " +
                                    "where accept " +
                                    "and friend = :id1 " +
                                    "union " +
                                    "select friend user_id " +
                                    "from friendship " +
                                    "where accept " +
                                    "and requester = :id2 ",
                            resultClass = UserID.class,
        resultSetMapping = "usersIDMapping"),
        @NamedNativeQuery(  name = "getUnAcceptedRequesters",
                            query = "select requester user_id" +
                                    "from friendship " +
                                    "where not accept " +
                                    "and friend = :id",
                            resultClass = UserID.class)
})
@SqlResultSetMapping(
        name = "usersIDMapping",
        entities = @EntityResult(
                entityClass = UserID.class,
                fields = {@FieldResult(name = "userID", column = "user_id")}))
public class Friendship {

    @EmbeddedId
    private FriendsPK id;

    @Column(name = "accept")
    private boolean accept;

}
