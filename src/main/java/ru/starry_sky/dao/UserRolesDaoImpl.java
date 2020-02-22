package ru.starry_sky.dao;

import ru.starry_sky.dao.interfases.UserRolesDao;
import ru.starry_sky.model.data_layer.UserRole;
import ru.starry_sky.model.data_layer.embedded_keys.UserRolesPK;

public class UserRolesDaoImpl extends GenericAbstractDaoImpl<UserRole, UserRolesPK> implements UserRolesDao {
}
