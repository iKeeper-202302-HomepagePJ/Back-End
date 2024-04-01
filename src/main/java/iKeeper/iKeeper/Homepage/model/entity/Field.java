package iKeeper.iKeeper.Homepage.model.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "field_table")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id", nullable = false)
    private Byte id;

    @Column(name = "field_name", length = 10)
    @ColumnDefault("'i-Keeper'")
    private String name;
}