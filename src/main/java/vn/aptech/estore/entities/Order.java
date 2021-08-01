package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Status status = Status.IN_PROGRESS;
    private String payment;
    private String shipping;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false, updatable = false)
    private Customer customer;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private Set<Product> products;
}
