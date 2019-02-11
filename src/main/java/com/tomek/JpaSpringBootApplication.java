package com.tomek;

import com.tomek.dao.BookDao;
import com.tomek.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class JpaSpringBootApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(JpaSpringBootApplication.class, args);
        BookDao bookDao = ctx.getBean(BookDao.class);

        //TEST CRUD for Book
        // CREATE new book
        Book book = new Book("1234567891234", "The Godfather", "Mario Puzo");
        bookDao.save(book);

        //UPDATE (with merge)
        Book book2 = new Book("987654321", "The Lord of The Rings", "JRR Tolkien");
        bookDao.update(book2); // the book doesnt exist in db so should create new one

        //READ
        Book findBook = bookDao.get(1L);
        System.out.println(findBook); // it should be "The Godfather" as he is 1st index

        //DELETE
        bookDao.remove(1L); // remove "The Godfather" and print removed index (1)
        Book book3 = bookDao.get(1L); //should not find book with this index
        System.out.println(book3); // should print null
    }
}

