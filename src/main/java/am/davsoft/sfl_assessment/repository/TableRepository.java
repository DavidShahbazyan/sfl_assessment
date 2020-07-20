package am.davsoft.sfl_assessment.repository;

import am.davsoft.sfl_assessment.model.CafeTable;
import am.davsoft.sfl_assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
public interface TableRepository extends JpaRepository<CafeTable, Long> {
    CafeTable findByIdAndWaiter_Id(Long id, Long waiterId);

    List<CafeTable> findAllByWaiter_Id(Long waiterId);
}
