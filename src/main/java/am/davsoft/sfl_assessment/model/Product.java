package am.davsoft.sfl_assessment.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Entity
@Table(name = "products")
@Data
public class Product extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;
}
