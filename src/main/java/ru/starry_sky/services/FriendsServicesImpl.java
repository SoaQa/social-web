package ru.starry_sky.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.User;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;
import ru.starry_sky.services.interfaces.FriendsServices;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FriendsServicesImpl implements FriendsServices {
    @Autowired
    private FriendsDao friendsDao;

    public boolean friendRequest(FriendsPK friendsPK){
        Friendship friendship = new Friendship();
        friendship.setId(friendsPK);
        friendsDao.save(friendship);
        return true;
    }

    public boolean acceptFriendship(FriendsPK friendsPK){
        Friendship friendship = friendsDao.getByID(friendsPK);
        friendship.setAccept(true);
        friendsDao.merge(friendship);
        return true;
    }


}
