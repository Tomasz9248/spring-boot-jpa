package com.tomek.model;

import java.io.Serializable;

import javax.persistence.*;

/* User class consist basic data about user
User_details class extends data about user
in below case User table has access to data from user_details table
its called one way relationship
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -7533343434173519473L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToOne
    // to make relationship two way create column with foreign key
    // class that contains "@JoinColumn" is an owner of relationship
    @JoinColumn(name = "id_details")
    private UserDetails details;

    User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetails getDetails() {
        return details;
    }

    public void setDetails(UserDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", "
                + "password=" + password + ", email=" + email
                + ", details=" + details + "]";
    }
}