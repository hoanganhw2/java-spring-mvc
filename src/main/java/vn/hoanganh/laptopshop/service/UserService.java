package vn.hoanganh.laptopshop.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.hoanganh.laptopshop.domain.Role;
import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.dto.ResigterDTO;
import vn.hoanganh.laptopshop.repository.OrderRepository;
import vn.hoanganh.laptopshop.repository.ProductRepository;
import vn.hoanganh.laptopshop.repository.RoleRepository;
import vn.hoanganh.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id);
    }

    public void deleteUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepository.findByName(name);

    }

    public User registerDTOtoUser(ResigterDTO resigterDTO) {
        User user = new User();
        user.setFullName(resigterDTO.getFirstName() + " " + resigterDTO.getLastName());
        user.setEmail(resigterDTO.getEmail());
        user.setPassword(resigterDTO.getPassword());
        return user;
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public long countUser() {

        return this.userRepository.count();
    }

    public long countProduct() {

        return productRepository.count();
    }

    public long countOrder() {

        return this.orderRepository.count();
    }
}
