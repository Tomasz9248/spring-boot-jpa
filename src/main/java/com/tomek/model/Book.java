package com.tomek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity // mark class as entity that can will be used to create table in database
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // mark the primary-key field=column
    @GeneratedValue // auto incrementation
    // every field will be considered as column in db

    // use object type not primitive. Primitive has default value "0"
    // that doesnt indicate whether its correct value or not.
    // Object type default value is "null" which is clear
    private Long id;
    private String isbn;
    private String title;
    //@Transient means that field is excluded from mapping
    private String author;

    public Book() {
    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    //Annotations might be placed over getter as well
    //@Id
    //@GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //@Transient
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}