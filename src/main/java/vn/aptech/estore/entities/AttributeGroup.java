package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "attribute_groups")
public class AttributeGroup extends AbstractEntity {
    private String name;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "attributeGroup", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Collection<Attribute> attributes = new HashSet<>();
}
