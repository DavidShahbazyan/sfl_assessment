package am.davsoft.sfl_assessment.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Entity
@Table(name = "orders_products")
@Data
public class ProductInOrder extends BaseEntity {
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "amount")
    private Integer amount;
}
