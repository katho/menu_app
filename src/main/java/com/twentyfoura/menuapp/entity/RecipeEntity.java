package com.twentyfoura.menuapp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "recipe")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String description;

    @Column(nullable = false)
    private String name;

    @OneToMany
    @JoinColumn(name="Id")
    private List<IngredientEntity> ingredient;
}
