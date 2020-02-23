package ru.starry_sky.dao;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.starry_sky.dao.interfases.GenericDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Slf4j
abstract class GenericAbstractDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    protected Class<T> cl;

    @Autowired
    protected SessionFactory sessionFactory;

    @PersistenceContext
    protected EntityManager entityManager;

    public void setGenericClass( Class< T > c ) {
        this.cl = c;
    }

    public void setAllData(List<T> l) {
        Session session = sessionFactory.openSession();
        try{
            for (T o: l
            ) {
                session.save(o);
            }
            session.getTransaction().commit();
            log.info("{} objects saved", l.size());
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

    public List<T> getAllData() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(this.cl);
        criteria.from(this.cl);
        List<T> result = session.createQuery(criteria).getResultList();
        log.info("{} objects found.", result.size());
        return result;
    }

    public T getByID(PK id) {
        Session session = sessionFactory.openSession();
        T result = session.get(this.cl, id);
        if (result == null){
            log.info("Not found object with id - {}", id);
        }
        else{
            log.info("Found object with id - {}", id);
        }
        return result;
    }

    public PK removeByID(PK id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            T ra = session.get(this.cl, id);
            if (ra != null) {
                session.remove(ra);
                session.getTransaction().commit();
                log.info("Object with id - {} removed", id);
                return id;
            }
            else{
                log.info("Object with id - {} not found", id);
                return null;
            }

        }
        catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
            return null;
        }
    }

    public void merge(T o){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.merge(o);
            session.getTransaction().commit();
            log.info("Object merged.");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

    public void save(T o){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            log.info("object saved.");
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

}
