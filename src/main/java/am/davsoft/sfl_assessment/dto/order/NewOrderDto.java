package am.davsoft.sfl_assessment.dto.order;

import am.davsoft.sfl_assessment.dto.product.ProductInOrderDto;
import am.davsoft.sfl_assessment.model.CafeOrder;
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
public class NewOrderDto {
    private Long tableId;
}
