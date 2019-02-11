package com.tomek.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "books") // table created in db will be named 'books'
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id") // column based on id will be named 'book_id'
    private Long id;
    @Column(nullable = false, length = 13, unique = true) // other (optional) options to setup for every column
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;

    public Book() {
    }

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    /*entity life cycle annotations added as methods in class allow to perform specified action in specified time
    Possible annotations are:
    @PrePersist, @PreUpdate, @PreRemove
    @PostLoad, @PostPersist, @PostUpdate, @PostRemove
    Those annotations must be void, cant take any argugments and cant be declared as static
     */
    @PrePersist // will perform after form is send
    public void prePersist() {
        System.out.println("Saving object: " + this);
    }

    @PostPersist // will perform when object is saved
    public void postPersist() {
        System.out.println("Saved object: " + this);
    }

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