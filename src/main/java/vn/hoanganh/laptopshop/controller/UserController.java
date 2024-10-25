package vn.hoanganh.laptopshop.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        List<User> list = this.userService.getAllUserByEmail("hoanganh@gmail.com");
        for (User us : list) {
            System.out.println(us.toString());
        }
        return "hello";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    public String getUserPage(Model model) {

        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // lấy ra danh sách người dùng
    @RequestMapping("/admin/user")
    public String getAllUserPage(Model model) {
        List<User> Users = this.userService.getAllUsers();
        model.addAttribute("users", Users);
        return "admin/user/tableuser";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUser(Model model, @ModelAttribute("newUser") User hanhUser) {
        this.userService.handleSaveUser(hanhUser);
        System.out.println("Run here" + hanhUser.toString());
        return "redirect:/admin/user";
    }

    // xem chi tiết 1 user
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {

        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);

        return "/admin/user/show";
    }

    // cập nhật thông tin người dùng
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currenUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currenUser);

        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User user1) {
        User currentUser = this.userService.getUserById(user1.getId());
        System.out.println("tim thay " + currentUser);
        if (currentUser != null) {
            currentUser.setAddress(user1.getAddress());
            currentUser.setFullName(user1.getFullName());
            currentUser.setPhone(user1.getPhone());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    // chuyen den trang xac nhan xoa
    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        User user = new User();
        user.setId(id);
        model.addAttribute("newUser", user);
        return "/admin/user/delete";
    }

    // xoa
    @PostMapping("/admin/user/delete")
    public String postDeleteUserPage(Model model, @ModelAttribute("newUser") User hoanganh) {
        this.userService.deleteUser(hoanganh.getId());
        return "redirect:/admin/user";
    }
}