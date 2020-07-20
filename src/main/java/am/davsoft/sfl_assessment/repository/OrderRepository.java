package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.model.CafeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface OrderRepository extends JpaRepository<CafeOrder, Long> {
    CafeOrder findByIdAndWaiter_Id(Long id, Long waiterId);

    List<CafeOrder> findAllByWaiter_Id(Long waiterId);
}
