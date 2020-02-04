package ru.starry_sky.model.data_layer.embedded_keys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class FriendsPK implements Serializable {

    @Column(name="user_id1")
    private Long UserID1;

    @Column(name = "user_id2")
    private Long UserID2;

    public FriendsPK(Long userID1, Long userID2) {
        UserID1 = userID1;
        UserID2 = userID2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FriendsPK)) return false;
        FriendsPK that = (FriendsPK) o;
        return Objects.equals(getUserID1(), that.getUserID1()) &&
                Objects.equals(getUserID2(), that.getUserID2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserID1(), getUserID2());
    }

}
