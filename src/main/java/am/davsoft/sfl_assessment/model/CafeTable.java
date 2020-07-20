package am.davsoft.sfl_assessment.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Entity
@Table(name = "tables")
@Data
public class CafeTable extends BaseEntity {
    @Column(name = "table_number")
    private Integer tableNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CafeOrder tableOrder;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "table_state")
    private TableState tableState;

    @OneToOne(fetch = FetchType.EAGER)
    private User waiter;
}
