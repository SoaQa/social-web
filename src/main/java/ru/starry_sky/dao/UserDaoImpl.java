package ru.starry_sky.dao;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.UserDao;
import ru.starry_sky.model.data_layer.User;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;


@Slf4j
public class UserDaoImpl extends GenericAbstractDaoImpl<User, Long> implements UserDao {
    public List<User> getUsersByIDSet(Set<Long> usersID){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        cq.select(root).where(root.get("id").in(usersID));
        return session.createQuery(cq).getResultList();
    }

    public User getUserByLogin(String login){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        cq.select(root).where(cb.equal(root.get("login"), login));
        try {
            return session.createQuery(cq).getSingleResult();
        }catch (NoResultException e){
            log.warn("User with login {} not found!", login);
            return null;
        }


    }

    public List<User> findUserByName(String name){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);
        Predicate namePredicate = cb.like(root.get("name"), name);
        cq.select(root).where(namePredicate);
        return session.createQuery(cq).getResultList();
    }
}
