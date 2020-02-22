package ru.starry_sky.dao;

import org.hibernate.Session;
import ru.starry_sky.dao.interfases.RoleDao;
import ru.starry_sky.model.data_layer.Role;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;



public class RoleDaoImpl extends GenericAbstractDaoImpl<Role, Long> implements RoleDao {

    @Override
    public Role getRoleByName(String name) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> cq = cb.createQuery(Role.class);

        Root<Role> root = cq.from(Role.class);
        cq.select(root).where(cb.equal(root.get("name"), name));
        Role role = session.createQuery(cq).getSingleResult();
        session.close();
        return role;
    }
}
