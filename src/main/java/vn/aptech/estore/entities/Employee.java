package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "employees")
public class Employee extends Person {
    private String role;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender=" + gender +
                '}';
    }
}
