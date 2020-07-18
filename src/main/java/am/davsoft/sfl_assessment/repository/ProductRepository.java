package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {
}