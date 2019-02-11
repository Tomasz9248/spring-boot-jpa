package com.tomek.dao;

import com.tomek.model.Book;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext // EntityManager injection
    private EntityManager entityManager;


    public BookDaoImpl() {
    }

    @Transactional // spring automatically opens and closes transaction
    public void save(Book book) {
        entityManager.persist(book);
    }

    // READ is the only one CRUD operation that doesnt require transaction
    public Book get(Long id) {
        Book book = entityManager.find(Book.class, id); // find() is EntityManager's method
        return book;
    }

    @Transactional
    public void update(Book book) {
        // obj passed as an argument must have defined primary key
        // merge is based on primary key
        entityManager.merge(book);
        // merge() looks for object in db with primary key same as passed obj in argument
        // if its in db then update. IF NOT CREATES NEW OBJECT IN DB.
    }

    /*ALTERNATIVE WAY
    @Transactional
    public void update(Book book) {
        //check for object in db
        Book find = entityManager.find(Book.class, book.getId());
        if(find != null) { // IF FOUND UPDATE DATA, IF NOT RETURN NULL
            find.setTitle(book.getTitle());
            find.setIsbn(book.getIsbn());
            find.setAuthor(book.getAuthor());
        }
    }
*/
    @Transactional
    public void remove(Long bookId) {
        Book objToRemove = entityManager.find(Book.class, bookId); // first find object
        entityManager.remove(objToRemove); // then remove
        System.out.println(objToRemove.getId()); // even after deleting table row its is still possible to access java object;
    }
}
