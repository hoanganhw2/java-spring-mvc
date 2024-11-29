package vn.hoanganh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.Cart;
import vn.hoanganh.laptopshop.domain.CartDetail;
import vn.hoanganh.laptopshop.domain.Product;

public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    public CartDetail save(CartDetail cartDetail);

    boolean existsByCartAndProduct(Cart cart, Product product);

    CartDetail findByCartAndProduct(Cart cart, Product product);

}
