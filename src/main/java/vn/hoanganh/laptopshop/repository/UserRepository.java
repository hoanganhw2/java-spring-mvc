package vn.hoanganh.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.hoanganh.laptopshop.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // thêm mới người dùng
    public User save(User user);

    public List<User> findByEmail(String email);

    public List<User> findAll();

    public User findById(long id);

    public void deleteById(long id);
}
