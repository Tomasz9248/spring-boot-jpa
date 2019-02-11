package com.tomek.dao;

import com.tomek.model.User;
import com.tomek.model.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    public UserDaoImpl() {
    }

    // its impossible to save object that is related to unsaved object!
    @Transactional
    public void save(User user) {
        UserDetails details = user.getDetails();
        if (details != null && details.getId() == null) {  // check if details are set to user and not saved in db
            entityManager.persist(details); // if yes save details to db
        }
        entityManager.persist(user); // after that save User obj with set details
    }

    public User get(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Transactional
    public void update(User user) {
        UserDetails details = user.getDetails();
        if (details != null) {
            details = entityManager.merge(details);
            user.setDetails(details);
        }
        entityManager.merge(user);
    }

    // first find User_Details connected to user and delete them. Then delete user
    @Transactional
    public void delete(User user) {
        User userToDelete = entityManager.merge(user);
        entityManager.remove(userToDelete.getDetails());
        entityManager.remove(userToDelete);
    }
}