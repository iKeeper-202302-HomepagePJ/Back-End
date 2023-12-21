package iKeeper.iKeeper.Homepage.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "field_table")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "field_id", nullable = false)
    private Short id;

    @Column(name = "field_name", nullable = false, length = 10)
    @ColumnDefault("'i-Keeper'")
    private String name;
}