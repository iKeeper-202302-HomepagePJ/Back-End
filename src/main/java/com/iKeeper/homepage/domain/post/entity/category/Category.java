package com.iKeeper.homepage.domain.post.entity.category;

import com.iKeeper.homepage.domain.post.dto.request.PostRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_large", referencedColumnName = "categorylarge_id")
    private CategoryLarge categoryLarge;

    @ManyToOne
    @JoinColumn(name = "category_small", referencedColumnName = "categorysmall_id")
    private CategorySmall categorySmall;

    @Builder
    public Category(CategoryLarge categoryLarge, CategorySmall categorySmall) {

        this.categoryLarge = categoryLarge;
        this.categorySmall = categorySmall;
    }

    public static Category createCategory(PostRequest postRequest) {

        Category category = Category.builder()
                .categoryLarge(postRequest.getCategory().getCategoryLarge())
                .categorySmall(postRequest.getCategory().getCategorySmall())
                .build();
        return category;
    }
}
