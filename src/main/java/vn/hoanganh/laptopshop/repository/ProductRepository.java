package vn.hoanganh.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // lưu một sản phẩm
    public Product save(Product product);

    // lấy ra tất cả danh sách sản phẩm
    public List<Product> findAll();

    // tim san pham theo id
    public Product findById(long id);

    // xoa san pham theo id
    public void deleteById(Long id);

}
