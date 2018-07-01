package com.hateit.interfaces.repositories;

import com.hateit.models.Category;
import com.hateit.models.Post;
import com.hateit.models.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class CategoryRepository extends CRUDRepository<Category>{
    public abstract Category getByName(String name) throws SQLException;
    public abstract List<Post> getAllPostByName(String name) throws SQLException;
}
