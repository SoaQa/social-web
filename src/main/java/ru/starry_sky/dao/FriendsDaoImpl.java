package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;


@Slf4j
public class FriendsDaoImpl extends GenericAbstractDaoImpl<Friendship, Long> implements FriendsDao {
}
