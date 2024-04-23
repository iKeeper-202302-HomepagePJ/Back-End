package com.iKeeper.homepage.domain.post.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Byte id;

    @ManyToOne
    @JoinColumn(name = "category_large", referencedColumnName = "categorylarge_id")
    private CategoryLarge categoryLarge;

    @ManyToOne
    @JoinColumn(name = "category_small", referencedColumnName = "categorysmall_id")
    private CategorySmall categorySmall;
}
