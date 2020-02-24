package ru.starry_sky.dao;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import ru.starry_sky.dao.interfases.CommunitiesDao;
import ru.starry_sky.model.data_layer.Community;
import ru.starry_sky.model.data_layer.CommunityID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CommunitiesDaoImpl extends GenericAbstractDaoImpl<Community, Long> implements CommunitiesDao {
    public List<Community> getUserCommunities(Long id){
        List<CommunityID> communityIDS = entityManager.createNamedQuery("getUserCommunitiesID", CommunityID.class)
                .setParameter("id", id)
                .getResultList();
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Community> cq = cb.createQuery(Community.class);
        List<Long> ids = new ArrayList<>();
        for (CommunityID c: communityIDS
             ) {ids.add(c.getCommunityID());
        }
        Root<Community> root = cq.from(Community.class);
        cq.select(root).where(root.get("id").in(ids));
        return session.createQuery(cq).getResultList();

    }
}
