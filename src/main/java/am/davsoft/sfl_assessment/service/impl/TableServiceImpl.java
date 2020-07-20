package am.davsoft.sfl_assessment.service.impl;

import am.davsoft.sfl_assessment.model.*;
import am.davsoft.sfl_assessment.repository.TableRepository;
import am.davsoft.sfl_assessment.service.TableService;
import am.davsoft.sfl_assessment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Service
@Slf4j
public class TableServiceImpl implements TableService {
    private final TableRepository tableRepository;

    public TableServiceImpl(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public CafeTable create(CafeTable table) {
        table.setTableState(TableState.FREE);
        table.setStatus(Status.ACTIVE);
        CafeTable newTable = tableRepository.save(table);
        log.info("IN create - table successfully created with id: {}", newTable.getId());
        return newTable;
    }

    @Override
    public CafeTable update(CafeTable table) {
        CafeTable updatedTable = tableRepository.save(table);
        log.info("IN update - table successfully updated with id: {}", updatedTable.getId());
        return updatedTable;
    }

    @Override
    public List<CafeTable> getAll() {
        List<CafeTable> tableList = tableRepository.findAll();
        log.info("IN getAll - {} tables found", tableList.size());
        return tableList;
    }

    @Override
    public List<CafeTable> findAllByWaiterId(Long id) {
        List<CafeTable> tableList = tableRepository.findAllByWaiter_Id(id);
        log.info("IN findByWaiter - {} tables found", tableList.size());
        return tableList;
    }

    @Override
    public CafeTable findById(Long id) {
        CafeTable table = tableRepository.findById(id).orElse(null);
        if (table == null) {
            log.warn("IN findById - no table found by id: {}", id);
            return null;
        }
        log.info("IN findById - table found by id: {}", id);
        return table;
    }

    @Override
    public CafeTable findByIdAndWaiterId(Long id, Long waiterId) {
        CafeTable table = tableRepository.findByIdAndWaiter_Id(id, waiterId);
        log.info("IN findByIdAndWaiterId - table found by id: {} and waiterId: {}", id, waiterId);
        return table;
    }

    @Override
    public void delete(Long id) {
        tableRepository.deleteById(id);
        log.info("IN delete - table with id: {} successfully deleted", id);
    }
}
