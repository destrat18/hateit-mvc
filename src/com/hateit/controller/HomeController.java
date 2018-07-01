package com.hateit.controller;

import com.hateit.interfaces.services.PostService;
import com.hateit.interfaces.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@Controller
@SessionAttributes({"user"})
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    protected String showHome(Model model) throws Exception {
//        if(!model.containsAttribute("user")) {
//            model.addAttribute("login_user", postService.createUserEntity());
//            return "login";
//        }
        model.addAttribute("posts", postService.getAll());
        return "home";
    }

    @RequestMapping(value = {"/category/{cname}"}, method = RequestMethod.GET)
    protected String showHome(@PathVariable("cname") String cname, Model model) throws Exception {

        model.addAttribute("posts", postService.getAllByCategoryName(cname));
        return "home";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    protected String processLogin(@ModelAttribute("login_user") UserEntity loginUser, Model model) {
//        if (!model.containsAttribute("user")) {
//            UserEntity userEntity = postService.verifyUser(loginUser.getUsername(), loginUser.getPassword());
//            model.addAttribute("user", userEntity);
//        }
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    protected ModelAndView processLogout(HttpSession session, Model model) {
//        session.invalidate();
//        model.asMap().clear();
//        return new ModelAndView("redirect", "retitle", "You are successfully logged out!");
//    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.GET)
//    public ModelAndView showSignUp() {
//        return new ModelAndView("signup", "signup_user", postService.createUserEntity());
//    }
//
//    @RequestMapping(value = "/signup", method = RequestMethod.POST)
//    protected ModelAndView processSignup(@ModelAttribute("signup_user") UserEntity signupUser) {
//        postService.add(signupUser);
//        return new ModelAndView("redirect", "retitle", "You are successfully signed up!");
//    }
//
//    @ResponseStatus(value= HttpStatus.CONFLICT, reason="Data integrity violation")  // 409
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void conflict() {
//        //Nothing
//    }
//

//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleError(HttpServletRequest req, Exception ex) {
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("ex", ex);
////        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("exception");
//        return mav;
//    }

//    @ExceptionHandler(HateItException.class)
//    public String handleHateItException(HateItException ex, Model model) {
////        ModelAndView mav = new ModelAndView("exception", "ex", ex);
////        mav.addObject("ex", ex);
////        mav.setViewName("exception");
//        model.addAttribute("ex", ex);
//        return "exception";
//    }


}
