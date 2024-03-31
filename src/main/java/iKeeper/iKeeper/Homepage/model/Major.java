package iKeeper.iKeeper.Homepage.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "major_table")
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_id", nullable = false)
    private Long id;

    @Column(name = "major_name", length = 20, nullable = false)
    @ColumnDefault("'없음'")
    private String name;
}
