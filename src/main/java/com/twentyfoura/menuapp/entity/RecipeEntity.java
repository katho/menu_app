package com.twentyfoura.menuapp.entity;

import javax.persistence.*;
import java.util.List;


@Entity(name = "recipe")
public class RecipeEntity {
    @Id
    private Long id;

    @Column
    private String description;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name="Id")
    private List<IngredientEntity> ingredient;
}
