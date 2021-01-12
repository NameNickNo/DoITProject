package org.doit.dao;

import org.doit.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getByEmail(String email);

    void createUser(User user);
}
