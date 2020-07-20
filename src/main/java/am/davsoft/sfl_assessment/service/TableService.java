package am.davsoft.sfl_assessment.service;

import am.davsoft.sfl_assessment.model.CafeTable;
import am.davsoft.sfl_assessment.model.User;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface TableService {
    CafeTable create(CafeTable table);

    CafeTable update(CafeTable table);

    List<CafeTable> getAll();

    List<CafeTable> findAllByWaiterId(Long id);

    CafeTable findById(Long id);

    CafeTable findByIdAndWaiterId(Long id, Long waiterId);

    void delete(Long id);
}
