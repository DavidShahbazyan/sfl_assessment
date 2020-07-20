package am.davsoft.sfl_assessment.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Entity
@Table(name = "orders")
@Data
public class CafeOrder extends BaseEntity {
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orders_order_products",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "order_product_id", referencedColumnName = "id")})
    private List<ProductInOrder> orderedProducts;

    @Column(name = "total_amount")
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    private OrderState orderState;

    @OneToOne(fetch = FetchType.LAZY)
    private CafeTable cafeTable;

    @OneToOne(fetch = FetchType.LAZY)
    private User waiter;

    public void calculateTotalAmount() {
        if (orderedProducts != null && !orderedProducts.isEmpty()) {
            orderedProducts.forEach(productInOrder -> totalAmount = Double.sum(totalAmount, productInOrder.getProduct().getPrice() * productInOrder.getAmount()));
        }
    }
}
