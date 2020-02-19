package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;
import java.util.Set;

public interface FriendsDao extends GenericDao<Friendship, FriendsPK> {
    Set<Long> getFriendsIDSetByUserID(Long userID);
}
