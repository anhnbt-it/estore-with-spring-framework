package vn.aptech.estore.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "attribute_groups")
public class AttributeGroup extends AbstractEntity {
    private String name;

    @OneToMany(mappedBy = "attributeGroup", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Attribute> attributes = new ArrayList<>();
}
