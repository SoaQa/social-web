package ru.starry_sky.dao.interfases;

import ru.starry_sky.model.data_layer.Role;

public interface RoleDao extends GenericDao<Role, Long> {
    Role getRoleByName(String name);
}
