package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;

@Repository
@Slf4j
public class UserDaoImpl extends GenericAbstractDaoImpl<User, Long> implements UserDao {
}
