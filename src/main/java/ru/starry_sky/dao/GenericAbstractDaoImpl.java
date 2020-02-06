package ru.starry_sky.dao;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.starry_sky.dao.interfases.GenericDao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Slf4j
abstract class GenericAbstractDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
    protected Class<T> cl;

    @Autowired
    protected SessionFactory sessionFactory;

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
        return session.createQuery(criteria).getResultList();
    }

    public T getByID(PK id) {
        Session session = sessionFactory.openSession();
        return session.get(this.cl, id);
    }

    public PK removeByID(PK id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            T ra = session.get(this.cl, id);
            session.remove(ra);
            session.getTransaction().commit();
            return id;
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
        }catch (HibernateException e){
            System.out.println(e.getMessage());
            log.error(e.getMessage(), e.getStackTrace());
            session.getTransaction().rollback();
        }
    }

}
