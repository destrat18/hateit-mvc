package com.hateit.interfaces.services;

import com.hateit.models.Category;
import com.hateit.models.Comment;
import com.hateit.models.Post;
import com.hateit.models.User;

import java.util.ArrayList;
import java.util.List;

public interface PostService {
    public Post get(String id) throws Exception;
    public Post add(String title,
                    String content,
                    String categories,
                    User user
    ) throws Exception;
    public List<Post> getAll() throws Exception;
    public List<Post> getAllByUser(User user) throws Exception;
    public List<Post> getAllByCategoryName(String categoryName) throws Exception;
    public boolean hate(String postId, User user) throws Exception;
    public Comment comment(String postId, User user, String content) throws Exception;
    public boolean isExist(String id) throws Exception;

}
