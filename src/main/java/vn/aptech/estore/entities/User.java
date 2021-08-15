package vn.aptech.estore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends AbstractEntity {
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
    protected boolean enabled;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
}
