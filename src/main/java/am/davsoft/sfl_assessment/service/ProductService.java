package am.davsoft.sfl_assessment.service;

import am.davsoft.sfl_assessment.model.Product;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    List<Product> getAll();

    Product findById(Long id);

    void delete(Long id);
}
