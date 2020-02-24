package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.UserID;
import ru.starry_sky.model.data_layer.embedded_keys.FriendsPK;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public List<Friendship> getUnacceptedFriendships(Long userID, String ex){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friendship> cq = cb.createQuery(Friendship.class);

        Root<Friendship> root = cq.from(Friendship.class);
        Predicate predicateForFriendship;

        // Если нужны запросы мне то я друг, если мои запросы то я реквестер
        if (ex.equals("me")){
            predicateForFriendship
                = cb.equal(root.get("friend"), userID);
        }else {
            predicateForFriendship
                    = cb.equal(root.get("requester"), userID);
        }
        Predicate predicateForAccept
                = cb.equal(root.get("accept"), false);
        Predicate predicate
                = cb.and(predicateForFriendship, predicateForAccept);
        cq.select(root).where(predicate);
        return session.createQuery(cq).getResultList();

    }
}
