package com.tomek.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

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
    @ManyToMany(fetch = FetchType.EAGER) // select loading strategy
    @Fetch(FetchMode.SELECT) // make Hibernate to use more than 1 query to not double outcome 
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

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
        return "Order [id=" + id
                + ", orderDetails=" + orderDetails
                + ", client=" + client.getFirstName() + " " + client.getLastName() + products.size()
                + ",\n products=" + products + "]";
    }
}