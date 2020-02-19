package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
public class FriendsDaoImpl extends GenericAbstractDaoImpl<Friendship, FriendsPK> implements FriendsDao {
    public Set<Long> getFriendsIDSetByUserID(Long userID){
        List<Long> friendsID = entityManager.createNamedQuery("getUserFriendsID", Long.class)
                .setParameter(1, userID)
                .setParameter(2, userID)
                .getResultList();
        return new HashSet<>(friendsID);
    }
}
