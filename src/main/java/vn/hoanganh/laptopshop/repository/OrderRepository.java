package vn.hoanganh.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(long id, Pageable pageable);
}
