package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "suppliers")
public class Supplier extends AbstractEntity {
    private String name;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "company_address")
    private String companyAddress;
    @Column(name = "company_code")
    private String companyCode;
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
