package vn.hoanganh.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.hoanganh.laptopshop.domain.Order;
import vn.hoanganh.laptopshop.domain.OrderDetail;
import vn.hoanganh.laptopshop.service.OrderService;

@Controller
public class OderController {
    private OrderService orderService;

    public OderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getDashBoard(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(page - 1, 4);

        Page<Order> orderPage = this.orderService.getAllOrDer(pageable);
        List<Order> orders = orderPage.getContent();
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", orderPage.getTotalPages() - 1);

        return "admin/order/show";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(Model model, @PathVariable long id) {

        Optional<Order> order = this.orderService.getOrderById(id);
        model.addAttribute("order", order.get());
        model.addAttribute("orderId", id);
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String updateOreder(Model model, @ModelAttribute("order") Order olOrder) {
        Order updateOrder = this.orderService.getOrderById(olOrder.getId()).get();
        updateOrder.setStatus(olOrder.getStatus());
        this.orderService.addSavOrder(updateOrder);
        return "redirect:/admin/order";
    }

    @GetMapping("admin/order/delete/{id}")
    public String getPageDeleteOrder(Model model, @PathVariable long id) {
        model.addAttribute("orderDel", new Order());
        model.addAttribute("id", id);
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String deleteOrder(@ModelAttribute("orderDel") Order order) {

        Order orderDel = this.orderService.getOrderById(order.getId()).get();
        List<OrderDetail> orderDetails = orderDel.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            this.orderService.deleteOrderDetailById(orderDetail.getId());
        }
        this.orderService.deleteOrderById(orderDel.getId());
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/detail/{id}")
    public String getOrderDetailPage(Model model, @PathVariable long id) {
        Order order = this.orderService.getOrderById(id).get();
        List<OrderDetail> orderDetails = order.getOrderDetails();
        System.out.println(orderDetails.size());
        model.addAttribute("orderDetails", orderDetails);
        model.addAttribute("orderid", id);
        return "admin/order/detail";
    }
}
