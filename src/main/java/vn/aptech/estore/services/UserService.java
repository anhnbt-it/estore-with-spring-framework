package vn.aptech.estore.services;

import vn.aptech.estore.entities.User;

import java.util.Optional;

public interface UserService {
    Iterable<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean existByEmail(String email);

    boolean existsByUsername(String username);

    void deleteById(Long id);

}
