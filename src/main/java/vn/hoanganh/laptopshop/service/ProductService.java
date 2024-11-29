package vn.hoanganh.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoanganh.laptopshop.domain.Cart;
import vn.hoanganh.laptopshop.domain.CartDetail;
import vn.hoanganh.laptopshop.domain.Order;
import vn.hoanganh.laptopshop.domain.OrderDetail;
import vn.hoanganh.laptopshop.domain.Product;
import vn.hoanganh.laptopshop.domain.Product_;
import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.dto.ProductCriteriaDTO;
import vn.hoanganh.laptopshop.repository.CartDetailRepository;
import vn.hoanganh.laptopshop.repository.CartRepository;
import vn.hoanganh.laptopshop.repository.OrderDetailRepository;
import vn.hoanganh.laptopshop.repository.OrderRepository;
import vn.hoanganh.laptopshop.repository.ProductRepository;
import vn.hoanganh.laptopshop.service.specification.*;
@Service
public class ProductService {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private CartDetailRepository cartDetailRepository;
    private UserService userService;
    private OrderRepository orderRepository;
    private OrderDetailRepository orderDetailRepository;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService, OrderRepository orderRepository,
            OrderDetailRepository orderDetailRepository) {
        this.productRepository = productRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
    }


   public Page<Product> fetchProductsWithSpec(Pageable page, String name) {
       return this.productRepository.findAll(ProductSpecs.nameLike(name),page );
   }
   public Page<Product> fetchProductsWithSpec(Pageable page, ProductCriteriaDTO productCriteriaDTO) {
       if (productCriteriaDTO.getTarget() == null
               && productCriteriaDTO.getFactory() == null
               && productCriteriaDTO.getPrice() == null) {
           return this.productRepository.findAll(page);
       }

       Specification<Product> combinedSpec = Specification.where(null);

       if (productCriteriaDTO.getTarget() != null && productCriteriaDTO.getTarget().isPresent()) {
           Specification<Product> currentSpecs = ProductSpecs.matchListTarget(productCriteriaDTO.getTarget().get());
           combinedSpec = combinedSpec.and(currentSpecs);
       }
       if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
           Specification<Product> currentSpecs = ProductSpecs.matchListFactory(productCriteriaDTO.getFactory().get());
           combinedSpec = combinedSpec.and(currentSpecs);
       }

       if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
           Specification<Product> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
           combinedSpec = combinedSpec.and(currentSpecs);
       }

       return this.productRepository.findAll(combinedSpec, page);
   }

   // case 6
   public Specification<Product> buildPriceSpecification(List<String> price) {
       Specification<Product> combinedSpec = (root, query, criteriaBuilder) -> criteriaBuilder.disjunction();
       for (String p : price) {
           double min = 0;
           double max = 0;

           // Set the appropriate min and max based on the price range string
           switch (p) {
               case "duoi-10-trieu":
                   min = 0;
                   max = 10000000;
                   break;
               case "10-15-trieu":
                   min = 10000000;
                   max = 15000000;
                   break;
               case "15-20-trieu":
                   min = 15000000;
                   max = 20000000;
                   break;
               case "tren-20-trieu":
                   min = 20000000;
                   max = 200000000;
                   break;
           }

           if (min != 0 && max != 0) {
               Specification<Product> rangeSpec = ProductSpecs.matchMultiplePrice(min, max);
               combinedSpec = combinedSpec.or(rangeSpec);
           }
       }

       return combinedSpec;
   }




    public Page<Product> fetchProducts(Pageable page) {
        return this.productRepository.findAll(page);
    }

    public void createProduct(Product product) {
        this.productRepository.save(product);
    }

    public Page<Product> getAllProduct(Pageable page) {
        return productRepository.findAll(page);
    }

    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public Optional<Product> getProdcutById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session,long quantity) {

    	  User user = this.userService.getUserByEmail(email);
          if (user != null) {
              // check user đã có Cart chưa ? nếu chưa -> tạo mới
              Cart cart = this.cartRepository.findByUser(user);

              if (cart == null) {
                  // tạo mới cart
                  Cart otherCart = new Cart();
                  otherCart.setUser(user);
                  otherCart.setSum(0);

                  cart = this.cartRepository.save(otherCart);
              }

              // save cart_detail
              // tìm product by id

              Optional<Product> productOptional = this.productRepository.findById(productId);
              if (productOptional.isPresent()) {
                  Product realProduct = productOptional.get();

                  // check sản phẩm đã từng được thêm vào giỏ hàng trước đây chưa ?
                  CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                  //
                  if (oldDetail == null) {
                      CartDetail cd = new CartDetail();
                      cd.setCart(cart);
                      cd.setProduct(realProduct);
                      cd.setPrice(realProduct.getPrice());
                      cd.setQuantity(quantity);
                      this.cartDetailRepository.save(cd);

                      // update cart (sum);
                      int s = cart.getSum() + 1;
                      cart.setSum(s);
                      this.cartRepository.save(cart);
                      session.setAttribute("sum", s);
                  } else {
                      oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                      this.cartDetailRepository.save(oldDetail);
                  }

              }

          }

    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }

    public void handleRemoveCartDetail(long CartDetailId, HttpSession session) {
        Optional<CartDetail> cartOptional = this.cartDetailRepository.findById(CartDetailId);

        if (cartOptional.isPresent()) {
            CartDetail cartDetail = cartOptional.get();
            Cart currentCart = cartDetail.getCart();
            this.cartDetailRepository.deleteById(CartDetailId);
            // update cart
            if (currentCart.getSum() > 1) {
                int s = currentCart.getSum() - 1;
                currentCart.setSum(s);
                session.setAttribute("sum", s);
                this.cartRepository.save(currentCart);
            } else {
                // delete cart
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", "");
            }

        }

    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            Optional<CartDetail> cdOptional = this.cartDetailRepository.findById(cartDetail.getId());
            if (cdOptional.isPresent()) {
                CartDetail currentCartDetail = cdOptional.get();
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.cartDetailRepository.save(currentCartDetail);
            }
        }
    }

    public void handlePlaceOrder(User user, HttpSession session, String receiverName, String receiverPhone,
            String receiverAddress) {

        // b1 tao don hang
        Cart cart = this.cartRepository.findByUser(user);
        if (cart != null) {
            List<CartDetail> cartDetails = cart.getCartDetails();
            if (cartDetails != null) {
                Order order = new Order();
                order.setUser(user);
                order.setReceiverName(receiverName);
                order.setReceiverPhone(receiverPhone);
                order.setReceiverAddress(receiverAddress);
                order = this.orderRepository.save(order);
                order.setStatus("CHỜ XÁC NHẬN");
                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = this.orderRepository.save(order);

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cd.getProduct());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    this.orderDetailRepository.save(orderDetail);
                }
                // delete cart detail
                for (CartDetail cd : cartDetails) {
                    this.cartDetailRepository.deleteById(cd.getId());
                }
                this.cartRepository.deleteById(cart.getId());

                // update session
                session.setAttribute("sum", "");
            }
        }

    }

}
