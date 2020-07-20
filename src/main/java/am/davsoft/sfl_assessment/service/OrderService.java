package am.davsoft.sfl_assessment.service;

import am.davsoft.sfl_assessment.model.CafeOrder;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface OrderService {
    CafeOrder create(CafeOrder order);

    CafeOrder update(CafeOrder order);

    List<CafeOrder> getAll();

    List<CafeOrder> findAllByWaiterId(Long id);

    CafeOrder findById(Long id);

    CafeOrder findByIdAndWaiterId(Long id, Long waiterId);

    void delete(Long id);
}
