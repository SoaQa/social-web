package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import java.util.List;
import java.util.Set;

public interface FriendsServices {
    boolean friendRequest(FriendsPK friendsPK);
    boolean acceptFriendship(boolean accept, FriendsPK friendsPK);
    List<Friendship> getUnacceptedFriendships(Long userID, String ex);
}
