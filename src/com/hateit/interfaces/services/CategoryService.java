package com.hateit.interfaces.services;

import com.hateit.models.Category;
import com.hateit.models.Post;
import com.hateit.models.User;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll() throws Exception;
    public List<Category> getAllByUser(User user) throws Exception;
    public void updatePostCategory(String pid, String categories, User currentUser) throws Exception;
    public void addToPost(Post post, String name) throws Exception;
}
