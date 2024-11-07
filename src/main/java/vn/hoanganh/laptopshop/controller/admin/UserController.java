package vn.hoanganh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.service.UploadService;
import vn.hoanganh.laptopshop.service.UserService;

@Controller
public class UserController {
    private PasswordEncoder passwordEncoder;
    private final UserService userService;
    private UploadService uploadService;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.uploadService = uploadService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/admin/user/create")
    public String getUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    // lấy ra danh sách người dùng
    @GetMapping("/admin/user")
    public String getAllUserPage(Model model) {
        List<User> Users = this.userService.getAllUsers();
        model.addAttribute("users", Users);
        return "admin/user/show";
    }

    @PostMapping("/admin/user/create") // ---------------------------------------------------------------------- POST
    public String createUser(Model model, @ModelAttribute("newUser") @Valid User hanhUser,
            BindingResult newUserBindingResult,
            @RequestParam("userFile") MultipartFile file) {

        List<FieldError> errors = newUserBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        // validate
        if (newUserBindingResult.hasErrors()) {
            return "admin/user/create";
        }

        //
        String avatar = this.uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(hanhUser.getPassword());

        hanhUser.setAvatar(avatar);
        hanhUser.setPassword(hashPassword);
        hanhUser.setRole(this.userService.getRoleByName(hanhUser.getRole().getName()));
        // save
        this.userService.handleSaveUser(hanhUser);
        return "redirect:/admin/user";

    }

    // xem chi tiết 1 user
    @RequestMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {

        User user = this.userService.getUserById(id);
        model.addAttribute("id", id);
        model.addAttribute("user", user);

        return "/admin/user/detail";
    }

    // truy cập trang cập nhật thông tin người dùng
    @RequestMapping("/admin/user/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        User currenUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currenUser);

        return "/admin/user/update";
    }

    // cập nhật thông tin người dùng
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