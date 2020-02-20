package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.UserID;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class FriendsDaoImpl extends GenericAbstractDaoImpl<Friendship, FriendsPK> implements FriendsDao {
    public Set<Long> getFriendsIDSetByUserID(Long userID){
        List<UserID> friendsID = entityManager.createNamedQuery("getUserFriendsID", UserID.class)
                .setParameter("id1", userID)
                .setParameter("id2", userID)
                .getResultList();
        Set<Long> users = new HashSet<>();
        for (UserID u: friendsID
             ) {
            users.add(u.getUserID());

        }
        return users;
    }
}
