package com.hateit.services;

import com.hateit.common.HateItException;
import com.hateit.common.Utility;
import com.hateit.interfaces.repositories.*;
import com.hateit.interfaces.services.PostService;
import com.hateit.interfaces.services.UserService;
import com.hateit.models.*;
import com.j256.ormlite.stmt.QueryBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletException;
import javax.xml.ws.http.HTTPException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceX implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HateRepository hateRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post get(String id) throws Exception {
        Post post = postRepository.getById(id);
        if(post == null)
            throw new HTTPException(404);
        return post;
    }

    @Override
    public Post add(String title,
                    String content,
                    String categories,
                    User user,
                    MultipartFile file) throws Exception
    {
        HateItException hateItException = new HateItException("/new-post","پست جدید");

        if(title == null || title.length() == 0)
            hateItException.getMessages().add("تیتر نباید خالی باشد!");
        if(content == null || content.length() == 0)
            hateItException.getMessages().add("محتوا نباید خالی باشد!");
        if(categories == null || categories.replace(" ", "").length() == 0)
            hateItException.getMessages().add("موضوع نباید خالی باشد!");

        if(hateItException.isHappen())
            throw hateItException;
        else
        {
            Post nPost = new Post();
            nPost.setTitle(title);
            nPost.setContent(content);
            nPost.setUser(user);
            nPost.setTimestamp(System.currentTimeMillis()/1000);
            nPost.setId(UUID.randomUUID().toString());
            for(String cat:categories.split(" "))
                if(cat.length() > 0) {
                    Category nCategory = new Category();
                    nCategory.setId(Utility.getUniqueId());
                    nCategory.setName(cat);
                    nCategory.setPost(nPost);
                    categoryRepository.add(nCategory);
                }
            nPost.setImage(Utility.uploadFile(file));
            if(hateItException.isHappen())
                throw hateItException;
            else {
                postRepository.add(nPost);
                return nPost;
            }
        }
    }

    @Override
    public List<Post> getAll() throws Exception {
        QueryBuilder<Post, String> queryBuilder = postRepository.getUserDao().queryBuilder();
        queryBuilder.orderBy("timestamp", false);
        return queryBuilder.query();
    }

    @Override
    public List<Post> getAllByUser(User user) throws Exception {
        return null;
    }

    @Override
    public List<Post> getAllByCategoryName(String categoryName) throws Exception {
        if(categoryRepository.getByName(categoryName) == null)
            throw new HTTPException(404);
        return categoryRepository.getAllPostByName(categoryName);
    }

    @Override
    public boolean hate(String postId, User user) throws Exception {
        Post post = postRepository.getById(postId);
        if(post == null)
            return false;

        Hate nHate = new Hate();
        nHate.setUser(user);
        nHate.setPost(post);
        nHate.setTimestamp(System.currentTimeMillis()/1000);
        nHate.setId(Utility.getUniqueId());
        hateRepository.add(nHate);
        return true;
    }


    @Override
    public Comment comment(String postId, User user, String content) throws Exception {
        Post post = postRepository.getById(postId);
        if(post == null)
            throw new HateItException("/", "خانه", "این پست وجود ندارد!");
        if(content == null || content.length() == 0)
            throw new HateItException("/", "خانه", "محتوا ابراز تاسف نباید خالی باشد!");
        Comment nComment = new Comment();
        nComment.setPost(post);
        nComment.setUser(user);
        nComment.setContent(content);
        nComment.setId(Utility.getUniqueId());
        nComment.setTimestamp(System.currentTimeMillis()/1000);
        commentRepository.add(nComment);
        return nComment;
    }

    @Override
    public boolean isExist(String id) throws Exception {
        return postRepository.isExist(id);
    }
}
