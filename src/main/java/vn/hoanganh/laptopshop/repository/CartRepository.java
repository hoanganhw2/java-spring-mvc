package vn.hoanganh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.Cart;
import vn.hoanganh.laptopshop.domain.User;

public interface CartRepository extends JpaRepository<Cart, Long> {
    public Cart findByUser(User user);

    public Cart save(Cart cart);

}
