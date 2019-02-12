package com.tomek.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/*
Order still has n:1 relationship with client from previous example
Now add ManyToMany one way relationship
client_order table has access to data stored in table products
 */
@Entity
@Table(name = "client_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;
    @Column(name = "details", length = 512)
    private String orderDetails;
    @ManyToMany // n:m annotation
    // list/set/map that stores objects of type that we want to get access to
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    /*
    Its possible to specify properties of table that stores n:m relationship

        @ManyToMany
    @JoinTable(name = "order_products",
       joinColumns = {@JoinColumn(name="order_id", referencedColumnName="id_order")},
       inverseJoinColumns = {@JoinColumn(name="product_id", referencedColumnName="id_product")}
    )
     */

    Order() {
    }

    public Order(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", products=" + products +
                ", client=" + client.getFirstName() + " " + client.getLastName() +
                ", orderDetails='" + orderDetails + '\'' +
                '}';
    }
}