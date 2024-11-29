package vn.hoanganh.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
