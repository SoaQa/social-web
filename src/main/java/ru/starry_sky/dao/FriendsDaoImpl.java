package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;


@Slf4j
public class FriendsDaoImpl extends GenericAbstractDaoImpl<Friendship, FriendsPK> implements FriendsDao {
}
