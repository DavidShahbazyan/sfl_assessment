package am.davsoft.sfl_assessment.dto.table;

import am.davsoft.sfl_assessment.dto.order.CafeOrderDto;
import am.davsoft.sfl_assessment.dto.user.UserDto;
import am.davsoft.sfl_assessment.model.CafeTable;
import am.davsoft.sfl_assessment.model.TableState;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CafeTableDto {
    private Long id;
    private Integer tableNumber;
    private CafeOrderDto tableOrder;
    private String tableState;
    private UserDto waiter;

    public CafeTable toModel() {
        CafeTable cafeTable = new CafeTable();
        cafeTable.setId(id);
        cafeTable.setTableNumber(tableNumber);
        if (tableOrder != null) {
            cafeTable.setTableOrder(tableOrder.toModel());
        }
        if (tableState != null) {
            cafeTable.setTableState(TableState.valueOf(tableState));
        }
        if (waiter != null) {
            cafeTable.setWaiter(waiter.toModel());
        }
        return cafeTable;
    }

    public static CafeTableDto fromModel(CafeTable model) {
        if (model == null) {
            return null;
        }
        CafeTableDto cafeTableDto = new CafeTableDto();
        cafeTableDto.setId(model.getId());
        cafeTableDto.setTableNumber(model.getTableNumber());
        cafeTableDto.setTableOrder(CafeOrderDto.fromModel(model.getTableOrder()));
        cafeTableDto.setTableState(model.getTableState().name());
        cafeTableDto.setWaiter(UserDto.fromModel(model.getWaiter()));
        return cafeTableDto;
    }
}
