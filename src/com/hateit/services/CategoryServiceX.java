package com.hateit.services;

import com.hateit.common.HateItException;
import com.hateit.common.Utility;
import com.hateit.interfaces.repositories.CategoryRepository;
import com.hateit.interfaces.repositories.PostRepository;
import com.hateit.interfaces.services.CategoryService;
import com.hateit.models.Category;
import com.hateit.models.Post;
import com.hateit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.util.*;

@Service
public class CategoryServiceX implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public List<Category> getAll() throws Exception {
        return categoryRepository.getAll();
    }

    @Override
    public List<Category> getAllByUser(User user) throws Exception {
        return categoryRepository.getAllByUser(user);
    }

    @Override
    public void updatePostCategory(String pid, String categories, User currentUser) throws Exception {
        Post post = postRepository.getById(pid);
        if(post == null)
            throw new HTTPException(404);
        if(currentUser.getValue() < post.getUser().getValue())
            throw new HateItException("/post/"+post.getImage(), post.getTitle(), "شما نمی‌توانید موضوع این پست را عوض کنید");
        String[] cats = categories.split(" ");
        Set<String> mySet = new HashSet<>(Arrays.asList(cats));
        List<String> cat_arr = new ArrayList<>();
        cat_arr.addAll(mySet);

        for(Category cat:post.getCategories())
            for(String s:cat_arr)
                if(cat.getName().equals(s)){
                    cat_arr.remove(s);
                    break;
                }

        for(String s:cat_arr)
            addToPost(post, s);

    }

    @Override
    public void addToPost(Post post, String name) throws Exception {
        Category nCategory = new Category();
        nCategory.setId(Utility.getUniqueId());
        nCategory.setName(name);
        nCategory.setPost(post);
        categoryRepository.add(nCategory);
    }
}
