package com.hateit.interfaces.services;

import com.hateit.models.User;

import java.sql.SQLException;

public interface UserService {
    public User getById(String id) throws Exception;
    public User signUpUser(String username, String password, String repassword) throws Exception;
    public void updateUser(User user) throws Exception;
    public User verifyUser(String username, String password) throws Exception;
    public User getByUsername(String username) throws Exception;

}
