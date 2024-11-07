package vn.hoanganh.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoanganh.laptopshop.domain.Product;
import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.dto.ResigterDTO;
import vn.hoanganh.laptopshop.service.ProductService;
import vn.hoanganh.laptopshop.service.UserService;

@Controller
public class HomePageController {
    private final ProductService productService;
    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    public HomePageController(PasswordEncoder passwordEncoder, ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProduct();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("userDTO", new ResigterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String hanldeRegister(@ModelAttribute("userDTO") ResigterDTO usResigterDTO) {
        User user = this.userService.registerDTOtoUser(usResigterDTO);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "client/auth/login";
    }
}
