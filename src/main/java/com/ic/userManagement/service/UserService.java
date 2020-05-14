package com.ic.userManagement.service;

import com.ic.userManagement.entity.User;

import java.util.List;

public interface UserService {
    User getByUserId(int userId);

    List<User> getAll();

    User createUser(User user);
}
