package com.iKeeper.homepage.domain.post.entity.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "parentcategory")
public class ParentCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parentcategory_id")
    private Byte id;

    @Column(name = "parentcategory_name")
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade =  CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Category> categories;
}
