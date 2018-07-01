package com.hateit.controller;

import com.hateit.interfaces.services.UserService;
import com.hateit.models.User;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"user"})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login", "/login.jsp"}, method = RequestMethod.GET)
    protected String showLogin(Model model) {
        if(!model.containsAttribute("user")) {
            return "login";
        }
        return "home";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    protected String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) throws Exception{
        if (!model.containsAttribute("user") )
        {
            User user = userService.verifyUser(username, password);
            model.addAttribute("user", user);
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    protected String logout(HttpSession session, Model model) throws Exception{
        if (model.containsAttribute("user"))
        {
            session.invalidate();
            model.asMap().clear();
        }
        return "redirect:/";
    }

    @RequestMapping(value = {"/sign-up", "/sign-up.jsp"}, method = RequestMethod.GET)
    public String showSignUp(Model model) {
        if(!model.containsAttribute("user")) {
            return "sign-up";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    protected String signUp(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("repassword") String repassword,
                            Model model) throws Exception
    {
        if(!model.containsAttribute("user")) {
            User user = userService.signUpUser(username, password, repassword);
            model.addAttribute("user", user);
        }
        return "redirect:/";
    }


    @RequestMapping(value = {"/user/{uid}"}, method = RequestMethod.GET)
    protected String showUser(@PathVariable("uid") String uid, Model model) throws Exception {
        model.addAttribute("user", userService.getById(uid));
        return "user";
    }

}
