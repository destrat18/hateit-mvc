package com.hateit.interfaces.services;

import com.hateit.models.Category;
import com.hateit.models.User;

import java.util.List;

public interface CategoryService {
    public Category get(String id) throws Exception;
    public Category getByName(int id) throws Exception;
    public Category add(String catName) throws Exception;
    public List<Category> getAll() throws Exception;
    public List<Category> getAllByUser(User user) throws Exception;
}
