package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.FriendsDao;
import ru.starry_sky.model.data_layer.Friendship;
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

    public List<Friendship> getUnacceptedFriendships(Long userID){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friendship> cq = cb.createQuery(Friendship.class);

        Root<Friendship> root = cq.from(Friendship.class);
        Predicate predicateForFriendship;
        predicateForFriendship
                = cb.equal(root.get("id").get("requester"), userID);
        Predicate predicateForAccept
                = cb.equal(root.get("accept"), false);
        Predicate predicate
                = cb.and(predicateForFriendship, predicateForAccept);
        cq.select(root).where(predicate);
        return session.createQuery(cq).getResultList();

    }
/*
    public boolean deleteFriendship(Long userID, Long friendID){
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        // create delete
        CriteriaDelete<Friendship> delete = cb.
                createCriteriaDelete(Friendship.class);

        // set the root class
        Root<Friendship> root = delete.from(Friendship.class);

        Predicate userRequesterPredicate = cb.equal(root.get("id").get("requester"), userID);
        Predicate userFriendPredicate = cb.equal(root.get("id").get("friend"), userID);

        Predicate user = cb.or(userRequesterPredicate, userFriendPredicate);

        Predicate friendRequesterPredicate = cb.equal(root.get("id").get("requester"), friendID);
        Predicate friendFriendPredicate = cb.equal(root.get("id").get("friend"), friendID);

        Predicate friend = cb.or(friendRequesterPredicate, friendFriendPredicate);

        // set where clause
        delete.where();

        // perform update
        session.createQuery(delete).executeUpdate();
    }


 */

}
