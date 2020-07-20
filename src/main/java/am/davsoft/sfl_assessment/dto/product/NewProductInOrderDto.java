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
public class NewProductInOrderDto {
    private Long orderId;
    private Long productId;
    private Integer amount;
}
