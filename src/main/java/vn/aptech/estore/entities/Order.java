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
    private Status status = Status.PENDING;
    private String payment;
    private String shipping;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private Set<Product> products;
}
