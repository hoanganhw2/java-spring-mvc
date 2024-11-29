package vn.hoanganh.laptopshop.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoanganh.laptopshop.domain.Product;
import vn.hoanganh.laptopshop.service.ProductService;
import vn.hoanganh.laptopshop.service.UploadService;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getDashBoard(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Product> productpage = this.productService.getAllProduct(pageable);
        List<Product> products = productpage.getContent();
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", productpage.getTotalPages() - 1);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getPageCreateProduct(Model model) {

        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult, @RequestParam("productFile") MultipartFile file) {
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError err : errors) {
            System.out.println(err.getDefaultMessage());
        }
        // validate
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        // set img
        String imgProduct = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(imgProduct);
        this.productService.createProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProdcutById(id).get();
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String hanldeUpdateProduct(@ModelAttribute("newProduct") @Valid Product pr,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) {
        // validate
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Product product = this.productService.getProdcutById(pr.getId()).get();
        if (product != null) {
            // cập nhật image
            if (!file.isEmpty()) {
                String img = this.uploadService.handleSaveUploadFile(file, "product");
                product.setImage(img);
                product.setName(pr.getName());
                product.setPrice(pr.getPrice());
                product.setQuantity(pr.getQuantity());
                product.setDetailDesc(pr.getDetailDesc());
                product.setShortDesc(pr.getShortDesc());
                product.setFactory(pr.getFactory());
                product.setTarget(pr.getTarget());

                this.productService.createProduct(product);
            }

        }

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProdcutDetailPage(Model model, @PathVariable long id) {
        Product product = this.productService.getProdcutById(id).get();
        model.addAttribute("id", id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        model.addAttribute("id", id);
        Product product = new Product();
        model.addAttribute("newProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProductPage(Model model, @ModelAttribute Product product) {
        this.productService.deleteProductById(product.getId());
        return "redirect:/admin/product";
    }
}
