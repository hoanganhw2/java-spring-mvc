package vn.hoanganh.laptopshop.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoanganh.laptopshop.domain.Order;
import vn.hoanganh.laptopshop.repository.OrderDetailRepository;
import vn.hoanganh.laptopshop.repository.OrderRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    public OrderService(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public Page<Order> getAllOrDer(Pageable pageable) {
        return this.orderRepository.findAll(pageable);
    }

    public Optional<Order> getOrderById(long id) {
        return this.orderRepository.findById(id);
    }

    public Order addSavOrder(Order order) {
        return this.orderRepository.save(order);
    }

    public void deleteOrderDetailById(long id) {
        this.orderDetailRepository.deleteById(id);
    }

    public void deleteOrderById(long id) {
        this.orderRepository.deleteById(id);
    }

    public Page<Order> getOrders(long id, Pageable pageable) {
        return this.orderRepository.findByUserId(id, pageable);
    }
}
