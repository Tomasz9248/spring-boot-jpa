package com.tomek.dao;

import com.tomek.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository // = @Component. Annotation @Repository is used when we implement DAO/repository template
@Transactional // if marked with @Transactional there is no need to open/close transaction manually
// annotation might be add to method or to class
public class BookDaoImpl implements BookDao {

    @PersistenceContext  // Spring automatically creates EM with EMF based on app context
    private EntityManager entityManager;


    public BookDaoImpl() {
    } // Constructor no longer have to call out objects


    public void save(Book book) {
       entityManager.persist(book);
    }

    public Book get(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }
}
