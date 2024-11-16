package com.dasom.tobi.dao;

import com.dasom.tobi.domain.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    User get(String Id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
