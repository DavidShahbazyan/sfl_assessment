package am.davsoft.sfl_assessment.dto.product;

import am.davsoft.sfl_assessment.model.ProductInOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInOrderDto {
    private Long id;
    private ProductDto product;
    private Integer amount;

    public ProductInOrder toModel() {
        ProductInOrder productInOrder = new ProductInOrder();
        productInOrder.setId(id);
        if (product != null) {
            productInOrder.setProduct(product.toModel());
        }
        productInOrder.setAmount(amount);
        return productInOrder;
    }

    public static ProductInOrderDto fromModel(ProductInOrder model) {
        if (model == null) {
            return null;
        }
        ProductInOrderDto productInOrderDto = new ProductInOrderDto();
        productInOrderDto.setId(model.getId());
        productInOrderDto.setProduct(ProductDto.fromModel(model.getProduct()));
        productInOrderDto.setAmount(model.getAmount());
        return productInOrderDto;
    }
}
