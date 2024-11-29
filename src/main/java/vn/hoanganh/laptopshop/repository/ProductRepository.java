package vn.hoanganh.laptopshop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // lưu một sản phẩm
    public Product save(Product product);

    // lấy ra tất cả danh sách sản phẩm
    public List<Product> findAll();

    
    // xoa san pham theo id
    public void deleteById(Long id);

    public Page<Product> findAll(Pageable page);

    Page<Product> findAll(Specification<Product> spec, Pageable page);
}
