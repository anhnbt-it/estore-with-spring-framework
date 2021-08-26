package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "products")
public class Product extends AbstractEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false, updatable = false)
    private Category category;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false, updatable = false)
    private Brand brand;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", nullable = false, updatable = false)
    private Supplier supplier;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_attributes",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
    )
    private Collection<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Collection<OrderDetail> orderDetails = new ArrayList<>();

    private String name;

    @Column(name = "unit_price")
    private Double unitPrice;

    @Column(name = "compare_at_price")
    private Double compareAtPrice;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    @Column(length = 5000)
    private String description;

    @Column(name = "units_in_stock")
    private Integer unitsInStock = 0;

    @Column(name = "units_on_order")
    private Integer unitsOnOrder = 0;

    private Boolean status = true;

    private Float discountPercent = 0f;

    public String getDiscountStr() {
        return (discountPercent > 0) ? "-" + (discountPercent * 100) + "%" : "0";
    }

    @Override
    public String toString() {
        return "Product{" +
                ", name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", description='" + description + '\'' +
                ", unitsInStock=" + unitsInStock +
                ", unitsOnOrder=" + unitsOnOrder +
                ", status=" + status +
                ", discount=" + discountPercent +
                '}';
    }
}
