package vn.aptech.estore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.exception.CommonException;
import vn.aptech.estore.repositories.UserRepository;
import vn.aptech.estore.validate.ValidateCommon;

import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        ValidateCommon.validateNullObject(id);
        return userRepository.findById(id);
    }

    @Override
    public User save(User user) {
        ValidateCommon.validateNullObject(user.getUsername());
        ValidateCommon.validateNullObject(user.getPassword());
        if (!ValidateCommon.isValidUsername(user.getUsername())) {
            throw new CommonException("Tên tài khoản không hợp lệ. Chỉ chấp nhận chữ viết thường và chữ số");
        }
        if (!ValidateCommon.validatePassword(user.getPassword())) {
            throw new CommonException("Mật khẩu tối thiểu 6 ký tự");
        }
        if (existsByUsername(user.getUsername())) {
            throw new CommonException(messageSource.getMessage("validators.username.exists", new Object[]{user.getUsername()}, Locale.getDefault()));
        }
        if (existByEmail(user.getEmail())) {
            throw new CommonException(messageSource.getMessage("validators.email.exists", new Object[]{user.getEmail()}, Locale.getDefault()));
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        ValidateCommon.validateNullObject(username);
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        ValidateCommon.isValidUsername(username);
        ValidateCommon.validatePassword(password);
        return Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
    }

    @Override
    public boolean existByEmail(String email) {
        ValidateCommon.validateNullObject(email);
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        ValidateCommon.validateNullObject(username);
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        ValidateCommon.validateNullObject(id);
        userRepository.deleteById(id);
    }

}
