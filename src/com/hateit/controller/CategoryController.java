package com.hateit.controller;

import com.hateit.common.HateItException;
import com.hateit.interfaces.services.CategoryService;
import com.hateit.interfaces.services.PostService;
import com.hateit.interfaces.services.UserService;
import com.hateit.models.Post;
import com.hateit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes({"user"})
public class CategoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/category/{cname}"}, method = RequestMethod.GET)
    protected String showHome(@PathVariable("cname") String cname, Model model) throws Exception {

        model.addAttribute("posts", postService.getAllByCategoryName(cname));
        return "home";
    }

    @RequestMapping(value = {"/update-category/{pid}"}, method = RequestMethod.POST)
    protected String newPost(@PathVariable("pid") String pid,
                             @RequestParam("category") String category,
                             @ModelAttribute("user") User currentUser
    ) throws Exception
    {
        categoryService.updatePostCategory(pid, category, currentUser);
        categoryService.updatePostCategory(pid, category, currentUser);
        return "redirect:/post/"+pid;
    }

}
