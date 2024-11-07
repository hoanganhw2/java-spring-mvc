package vn.hoanganh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoanganh.laptopshop.domain.Product;
import vn.hoanganh.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public Product getProdcutById(long id) {
        return this.productRepository.findById(id);
    }
}
