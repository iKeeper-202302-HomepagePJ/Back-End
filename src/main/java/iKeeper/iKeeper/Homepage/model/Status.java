package iKeeper.iKeeper.Homepage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "status_table")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id", nullable = false)
    private Long id;

    @Column(name = "status_name", nullable = false)
    @ColumnDefault("'신입'")
    private String name;
}
