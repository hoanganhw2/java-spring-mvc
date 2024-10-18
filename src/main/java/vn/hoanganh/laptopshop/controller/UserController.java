package vn.hoanganh.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.hoanganh.laptopshop.service.UserService;


@Controller
public class UserController {

    private UserService userService ;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("test", "hello dai ca hoàng anh");
        model.addAttribute("test2", "hello from controller");
       return "hello";
    }
}