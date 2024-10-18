package vn.hoanganh.laptopshop.repository;

import org.springframework.data.repository.CrudRepository;

import vn.hoanganh.laptopshop.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User save(User user);

}
