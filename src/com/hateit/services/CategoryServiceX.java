package com.hateit.services;

import com.hateit.interfaces.repositories.CategoryRepository;
import com.hateit.interfaces.services.CategoryService;
import com.hateit.models.Category;
import com.hateit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceX implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category get(String id) throws Exception {
        return null;
    }

    @Override
    public Category getByName(int id) throws Exception {
        return null;
    }

    @Override
    public Category add(String catName) throws Exception {
        return null;
    }

    @Override
    public List<Category> getAll() throws Exception {
        return categoryRepository.getAll();
    }

    @Override
    public List<Category> getAllByUser(User user) throws Exception {
        return categoryRepository.getAllByUser(user);
    }
}
