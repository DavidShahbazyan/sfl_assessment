package am.davsoft.sfl_assessment.service.impl;

import am.davsoft.sfl_assessment.model.Product;
import am.davsoft.sfl_assessment.repository.ProductRepository;
import am.davsoft.sfl_assessment.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        Product newProduct = productRepository.save(product);
        log.info("IN create - product successfully created with id: {}", newProduct.getId());
        return newProduct;
    }

    @Override
    public Product update(Product product) {
        Product updatedProduct = productRepository.save(product);
        log.info("IN update - product successfully updated with id: {}", updatedProduct.getId());
        return updatedProduct;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = productRepository.findAll();
        log.info("IN getAll - {} products found", productList.size());
        return productList;
    }

    @Override
    public Product findById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            log.warn("IN findById - no product found by id: {}", id);
            return null;
        }
        log.info("IN findById - product found by id: {}", id);
        return product;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
        log.info("IN delete - product with id: {} successfully deleted", id);
    }
}
