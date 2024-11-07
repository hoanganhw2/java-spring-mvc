package vn.hoanganh.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoanganh.laptopshop.domain.Role;
import vn.hoanganh.laptopshop.domain.User;
import vn.hoanganh.laptopshop.dto.ResigterDTO;
import vn.hoanganh.laptopshop.repository.RoleRepository;
import vn.hoanganh.laptopshop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
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
}
