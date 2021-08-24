package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_attributes",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")}
    )
    private Set<Attribute> attributes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

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
    private Integer unitsInStock;

    @Column(name = "units_on_order")
    private Integer unitsOnOrder;

    private Boolean status;

    private Float discountPercent;

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
