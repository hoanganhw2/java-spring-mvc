package vn.hoanganh.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {

        return "hello";
    }

    @RequestMapping(value = "/admin/create", method = RequestMethod.GET)
    public String getUserPage(Model model) {

        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("newUser") User hanhUser) {
        this.userService.handleSaveUser(hanhUser);
        System.out.println("Run here" + hanhUser.toString());
        return "hello";
    }

}