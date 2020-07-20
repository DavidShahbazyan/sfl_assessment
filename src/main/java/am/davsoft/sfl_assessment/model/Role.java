package am.davsoft.sfl_assessment.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 20, 2020
 */
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
