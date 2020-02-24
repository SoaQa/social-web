package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import java.util.Set;

public interface FriendsServices {
    boolean friendRequest(FriendsPK friendsPK);
    boolean acceptFriendship(boolean accept, FriendsPK friendsPK);
}
