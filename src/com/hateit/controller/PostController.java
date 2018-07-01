package com.hateit.controller;

import com.hateit.common.HateItException;
import com.hateit.interfaces.services.PostService;
import com.hateit.interfaces.services.UserService;
import com.hateit.models.Comment;
import com.hateit.models.Hate;
import com.hateit.models.Post;
import com.hateit.models.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes({"user"})
public class PostController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/new-post"}, method = RequestMethod.GET)
    protected String showNewPost(Model model) {
        return "new-post";
    }

    @RequestMapping(value = {"/new-post"}, method = RequestMethod.POST)
    protected String newPost(@RequestParam("title") String title,
                             @RequestParam("content") String content,
                             @RequestParam("category") String category,
                             @ModelAttribute("user") User currentUser,
                             Model model) throws Exception
    {
        Post nPost = postService.add(title, content, category, currentUser);
        return "redirect:/post/"+nPost.getId();
    }

    @RequestMapping(value = {"/post/{id}"}, method = RequestMethod.GET)
    protected String showPost(@PathVariable("id") String id, Model model) throws Exception {
        Post nPost = postService.get(id);
        model.addAttribute("post", nPost);
        return "post";
    }

    @RequestMapping(value = {"/hate"}, method = RequestMethod.POST)
    @ResponseBody
    public String hate(@RequestBody String json,
                       @ModelAttribute("user") User currentUser) throws Exception {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject returnObject = new JSONObject();

        if(jsonObject.has("postId")) {
            if (postService.hate(jsonObject.getString("postId"), currentUser)) {
                returnObject.put("status", true);
                returnObject.put("hate_count", postService.get(jsonObject.getString("postId")).getHatesCount());
            }
        }
        else {
            returnObject.put("status", false);
        }
        return returnObject.toString();
    }

    @RequestMapping(value = {"/comment"}, method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String comment(@RequestBody String json,
                       @ModelAttribute("user") User currentUser) throws Exception {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject returnObject = new JSONObject();

        if(jsonObject.has("postId") && jsonObject.has("content")) {
            Comment nComment = postService.comment(
                    jsonObject.getString("postId"),
                    currentUser,
                    jsonObject.getString("content")
                    );

            returnObject.put("name", currentUser.getName());
            returnObject.put("content", nComment.getContent());
            returnObject.put("date", nComment.getDate());
            returnObject.put("image", currentUser.getImage());
            returnObject.put("commentId", nComment.getId());
        }
        else
            returnObject.put("status", false);

        return returnObject.toString();
    }



}
