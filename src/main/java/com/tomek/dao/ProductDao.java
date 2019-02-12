package com.tomek.dao;

import com.tomek.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Product get(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void save(Product product) {
        entityManager.persist(product);
    }

    public List<Product> getAll() {
        // in JPQL its necessary to use aliases
        final String getAll = "SELECT p FROM Product p";
        // TypedQuery interface extends Query interface. Its generic so doesnt need typecasting
        // queries are created by calling on EntityManager
        TypedQuery<Product> getAllQuery = entityManager.createQuery(getAll, Product.class);
        // if expect list of objects as result use getResultList() on query - in case of no date returns empty list. null-safe
        // if expect only 1 objec use getSingleObject() - exception if returned more than 1 record
        List<Product> resultList = getAllQuery.getResultList();
        return resultList;
    }

    public void deleteAll() {
        final String deleteAll = "DELETE FROM Product p";
        Query deleteAllQuery = entityManager.createQuery(deleteAll);
        //executeUpdate() returns number of record that were updated/removed
        deleteAllQuery.executeUpdate();
    }

    public List<Product> customGet(String jpqlQuery) {
        TypedQuery<Product> query = entityManager.createQuery(jpqlQuery, Product.class);
        return query.getResultList();
    }
}