package ru.starry_sky.services.interfaces;

import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

public interface FriendsServices {
    boolean friendRequest(FriendsPK friendsPK);
    boolean acceptFriendship(FriendsPK friendsPK);
}
