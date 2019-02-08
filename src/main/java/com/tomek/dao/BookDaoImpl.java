package com.tomek.dao;

import com.tomek.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository // = @Component. Annotation @Repository is used when we implement DAO/repository template
public class BookDaoImpl implements BookDao {

    @PersistenceUnit // JPA's annotation like @Autowired injects EntityManagerFactory type object to field
    private EntityManagerFactory emFactory;


    public BookDaoImpl() {
    } // Constructor no longer have to call out objects

    public void save(Book book) {
        EntityManager entityManager = emFactory.createEntityManager(); // create EntityManager obj through emFactory
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(book);
        tx.commit();
        entityManager.close(); // close only Entity Manager. EntityManagerFactory is ordered by Spring itself
    }

    public Book get(Long id) {
        EntityManager entityManager = emFactory.createEntityManager(); // create EntityManager obj
        Book book = entityManager.find(Book.class, id); // call find method on EntityManager obj to select book based on id column
        entityManager.close(); // close only Entity Manager. EntityManagerFactory is ordered by Spring itself
        return book;
    }
}
