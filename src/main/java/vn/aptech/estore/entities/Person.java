package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class Person extends AbstractEntity {
    protected static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    protected String firstName;
    protected String lastName;
    protected String email;
    @Column(length = 14)
    protected String phone;
    protected String username;
    protected String password;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    protected Date dateOfBirth;
    protected Boolean gender;

    public void setPassword(String password) {
        this.password = PASSWORD_ENCODER.encode(password);
    }
}
