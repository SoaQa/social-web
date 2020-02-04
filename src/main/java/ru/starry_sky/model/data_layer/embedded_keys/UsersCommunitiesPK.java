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
public class UsersCommunitiesPK implements Serializable {

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "community_id")
    private Long communityID;

    public UsersCommunitiesPK(Long userID, Long communityID) {
        this.userID = userID;
        this.communityID = communityID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersCommunitiesPK)) return false;
        UsersCommunitiesPK that = (UsersCommunitiesPK) o;
        return Objects.equals(getCommunityID(), that.getCommunityID()) &&
                Objects.equals(getUserID(), that.getUserID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommunityID(), getUserID());
    }
}
