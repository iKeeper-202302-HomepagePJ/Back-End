package iKeeper.iKeeper.Homepage.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "authority_table")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id", nullable = false)
    private Long id;

    @Column(name = "authority_name", length = 10)
    @ColumnDefault("'일반회원'")
    private String name;

    @Column(name = "authority_staff")
    @ColumnDefault("'0'")
    private Boolean staff;
}