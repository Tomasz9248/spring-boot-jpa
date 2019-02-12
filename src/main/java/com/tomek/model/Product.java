package com.tomek.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
/*
to create static query use @NamedQuery in entity that they operate on
name should be meaningful and look like EntityName.whatThisQueryExactlyDo
 */
@NamedQueries({
        @NamedQuery(name = "Product.getAll", query = "SELECT p FROM Product p"),
        @NamedQuery(name = "Product.deleteAll", query = "DELETE FROM Product p")
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String producer;
    private Double price;

    Product() {}

    public Product(String name, String producer, Double price) {
        this.name = name;
        this.producer = producer;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [id=" + id
                + ", name=" + name
                + ", producer=" + producer
                + ", price=" + price + "]";
    }
}