package am.davsoft.sfl_assessment.dto.order;

import am.davsoft.sfl_assessment.dto.product.ProductInOrderDto;
import am.davsoft.sfl_assessment.model.CafeOrder;
import am.davsoft.sfl_assessment.model.OrderState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeOrderDto {
    private Long id;
    private Double totalAmount;
    private String orderState;
    private List<ProductInOrderDto> orderedProducts;

    public CafeOrder toModel() {
        CafeOrder cafeOrder = new CafeOrder();
        cafeOrder.setId(id);
        cafeOrder.setTotalAmount(totalAmount);
        if (orderState != null) {
            cafeOrder.setOrderState(OrderState.valueOf(orderState));
        }
        if (orderedProducts != null) {
            cafeOrder.setOrderedProducts(orderedProducts.stream().map(ProductInOrderDto::toModel).collect(Collectors.toList()));
        }
        return cafeOrder;
    }

    public static CafeOrderDto fromModel(CafeOrder model) {
        if (model == null) {
            return null;
        }
        CafeOrderDto cafeOrderDto = new CafeOrderDto();
        cafeOrderDto.setId(model.getId());
        cafeOrderDto.setTotalAmount(model.getTotalAmount());
        cafeOrderDto.setOrderState(model.getOrderState().name());
        if (model.getOrderedProducts() != null) {
            cafeOrderDto.setOrderedProducts(model.getOrderedProducts().stream().map(ProductInOrderDto::fromModel).collect(Collectors.toList()));
        }
        return cafeOrderDto;
    }
}
