package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "attributes")
public class Attribute extends AbstractEntity {
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_group_id", nullable = false, updatable = false)
    private AttributeGroup attributeGroup;

    @ManyToMany(mappedBy = "attributes", fetch = FetchType.EAGER)
    private Set<Product> products;
}
