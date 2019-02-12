package com.tomek.dao;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

@Transactional
public abstract class GenericDao<T, K> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> type; // during execution defines what type of entity is under operation

    // Annotation manually ensures java compiler that result of this compilation will be safe
    // otherwise cause of lack of information compiler might have treated it like an error
    @SuppressWarnings("unchecked")
    GenericDao() {
        type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T get(K key) {
        T find = entityManager.find(type, key);
        return find;
    }

    public void update (T entity) {
        entityManager.merge(entity);
    }

    public void remove (T entity) {
        T toRemove = entityManager.merge(entity); // remove() methods works on managed objects. merge() makes it managed
        entityManager.remove(toRemove);
    }
}