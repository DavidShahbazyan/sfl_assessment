package am.davsoft.sfl_assessment.dto.table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableAssignmentDto {
    private Long tableId;
    private Long waiterId;
}
