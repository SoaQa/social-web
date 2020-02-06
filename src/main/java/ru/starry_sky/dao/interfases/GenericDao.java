package ru.starry_sky.dao.interfases;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, PK extends Serializable> {
    void setGenericClass( Class< T > c );
    List<T> getAllData();
    void setAllData(List<T> l);
    T getByID(PK id);
    PK removeByID(PK id);
    void merge(T o);
}

