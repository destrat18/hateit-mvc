package com.hateit.interfaces.repositories;

import com.hateit.models.User;

import java.sql.SQLException;

public abstract class UserRepository extends CRUDRepository<User>{
    public abstract User getUserByUsername(String username) throws SQLException;
}
