package com.tomek.model;

import com.tomek.model.Order;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/* In this example client class has access to orders data (one way)
Relationship type is OneToMany (1:n)
 */
@Entity
@Table(name = "clients")
public class Client implements Serializable {
    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;
    @Column(name="firstname", nullable=false)
    private String firstName;
    @Column(name="lastname", nullable=false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @OneToMany
    // @JoinColumn annotation creates new table in this owner class
    // column name based on 'name =' and referencedColumnName is primary key field
    // data is stored as a list; might be also set or map
    @JoinColumn(name = "client_id", referencedColumnName="id_client")
    private List<Order> orders;

    Client() {}

    public Client(String firstName, String lastName, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    @Override
    public String toString() {
        return "Client [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + ", address=" + address
                + ", orders=" + orders + "]";
    }
}