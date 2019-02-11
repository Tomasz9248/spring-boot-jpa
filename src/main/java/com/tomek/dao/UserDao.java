package com.tomek.dao;

import com.tomek.model.User;

public interface UserDao {

    public void save(User user);
    public User get (Long id);
    public void update(User user);
    public void delete(User user);
}
