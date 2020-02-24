package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.starry_sky.dao.interfases.WallMessagesDao;
import ru.starry_sky.model.data_layer.PrivateMessage;
import ru.starry_sky.model.data_layer.WallMessage;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
public class WallMessagesDaoImpl extends GenericAbstractDaoImpl<WallMessage, Long> implements WallMessagesDao {
    public List<WallMessage> getUserMessages(Long id){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WallMessage> cq = cb.createQuery(WallMessage.class);

        Root<WallMessage> root = cq.from(WallMessage.class);
        Predicate predicateForRecipient
                = cb.equal(root.get("recipientID"), id);
        cq.select(root).where(predicateForRecipient);
        return session.createQuery(cq).getResultList();
    }
}
