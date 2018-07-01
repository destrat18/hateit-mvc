package com.hateit.interfaces.services;

import com.hateit.models.Category;
import com.hateit.models.User;

public interface CategoryService {
    public Category get(String id) throws Exception;
    public Category getByName(int id) throws Exception;
    public Category add(String catName) throws Exception;
}
