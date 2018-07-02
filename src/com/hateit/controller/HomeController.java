package com.hateit.controller;

import com.hateit.common.HateItException;
import com.hateit.interfaces.services.CategoryService;
import com.hateit.interfaces.services.PostService;
import com.hateit.interfaces.services.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.http.HTTPException;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ControllerAdvice
@Controller
@SessionAttributes({"user"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    protected String showHome(Model model) throws Exception {
//        if(!model.containsAttribute("user")) {
//            model.addAttribute("login_user", postService.createUserEntity());
//            return "login";
//        }
        model.addAttribute("posts", postService.getAll());
        model.addAttribute("categories", categoryService.getAll());
        return "home";
    }


//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("ex", ex);
////        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("exception");
//        return mav;
//    }

    @ExceptionHandler(HateItException.class)
    public String handleHateItException(HateItException ex, Model model) {
//        ModelAndView mav = new ModelAndView("exception", "ex", ex);
//        mav.addObject("ex", ex);
//        mav.setViewName("exception");
        model.addAttribute("ex", ex);
        return "exception";
    }

    @ExceptionHandler(HTTPException.class)
    public String handleHttpException(HTTPException ex, Model model) {
//        ModelAndView mav = new ModelAndView("exception", "ex", ex);
//        mav.addObject("ex", ex);
//        mav.setViewName("exception");
        return "not-found";
    }


}
