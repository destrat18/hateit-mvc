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

    @RequestMapping(value = {"/comment"}, method = RequestMethod.POST)
    @ResponseBody
    public String hate(@RequestBody String json,
                       @ModelAttribute("user") User currentUser) throws Exception {
        JSONObject jsonObject = new JSONObject(json);

        JSONObject returnObject = new JSONObject();
        if(json.contains("postId") && json.contains("content")) {
            Comment comment = postService.comment(jsonObject.getString("postId"),
                    currentUser,
                    jsonObject.getString("content")
            );
            returnObject.put("name", currentUser.getName());
            returnObject.put("content", comment.getContent());
            returnObject.put("date", comment.getDate());
            returnObject.put("image", currentUser.getImage());
        }
        else
            throw new HateItException("/","خانه", "مشکلی با درخواست به وجود آمده‌است! لطفا دوباره امتحان کنید");

        return returnObject.toString();
    }

}
