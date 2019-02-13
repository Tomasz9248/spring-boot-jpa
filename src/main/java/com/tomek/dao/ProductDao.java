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
        TypedQuery<Product> getAllQuery = entityManager.createNamedQuery("Product.getAll", Product.class);
        List<Product> resultList = getAllQuery.getResultList();
        return resultList;
    }

    public List<Product> findByName(String name) {
        TypedQuery<Product> getByNameQuery = entityManager.createNamedQuery("Product.findByName", Product.class);
        // set parameter named name (':name' in query) to value passed in argument as name
        getByNameQuery.setParameter("name", name);
        List<Product> resultList = getByNameQuery.getResultList();
        return resultList;
    }

    public void deleteByProducer(String producer) {
        Query deleteByProducer = entityManager.createQuery("DELETE FROM Product p WHERE p.producer = :producer");
        // set producer param value to String producer passed as argument
        deleteByProducer.setParameter("producer", producer);
        deleteByProducer.executeUpdate();
    }

    public void deleteAll() {
        Query deleteAllQuery = entityManager.createNamedQuery("Product.deleteAll");
        deleteAllQuery.executeUpdate();
    }

    public List<Product> customGet(String jpqlQuery) {
        TypedQuery<Product> query = entityManager.createQuery(jpqlQuery, Product.class);
        return query.getResultList();
    }
}