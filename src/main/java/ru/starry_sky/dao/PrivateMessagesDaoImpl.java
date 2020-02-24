package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.PrivateMessagesDao;
import ru.starry_sky.model.data_layer.PrivateMessage;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Slf4j
public class PrivateMessagesDaoImpl extends GenericAbstractDaoImpl<PrivateMessage, Long> implements PrivateMessagesDao {
public List<PrivateMessage> getUserMessages(Long id){
    Session session = sessionFactory.openSession();
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<PrivateMessage> cq = cb.createQuery(PrivateMessage.class);

    Root<PrivateMessage> root = cq.from(PrivateMessage.class);
    Predicate predicateForSender
            = cb.equal(root.get("senderID"), id);
    Predicate predicateForRecipient
            = cb.equal(root.get("recipientID"), id);
    Predicate predicate
            = cb.or(predicateForSender, predicateForRecipient);
    cq.select(root).where(predicate);
    return session.createQuery(cq).getResultList();
}
}
