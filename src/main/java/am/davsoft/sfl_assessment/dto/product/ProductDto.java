package am.davsoft.sfl_assessment.dto.product;

import am.davsoft.sfl_assessment.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public Product toModel() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return product;
    }

    public static ProductDto fromModel(Product model) {
        if (model == null) {
            return null;
        }
        ProductDto productDto = new ProductDto();
        productDto.setId(model.getId());
        productDto.setName(model.getName());
        productDto.setDescription(model.getDescription());
        productDto.setPrice(model.getPrice());
        return productDto;
    }
}
