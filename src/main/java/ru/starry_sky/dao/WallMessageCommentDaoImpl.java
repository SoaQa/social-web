package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.WallMessageCommentDao;
import ru.starry_sky.model.data_layer.WallMessageComment;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Slf4j
public class WallMessageCommentDaoImpl extends GenericAbstractDaoImpl<WallMessageComment, Long> implements WallMessageCommentDao {
    public List<WallMessageComment> getMessageComments(Long messageID){
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<WallMessageComment> cq = cb.createQuery(WallMessageComment.class);

        Root<WallMessageComment> root = cq.from(WallMessageComment.class);
        Predicate predicateForRecipient
                = cb.equal(root.get("messageID"), messageID);
        cq.select(root).where(predicateForRecipient);
        return session.createQuery(cq).getResultList();
    }
}
