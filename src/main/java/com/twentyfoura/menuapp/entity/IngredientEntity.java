package com.twentyfoura.menuapp.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "INGREDIENTS")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ingredientId;

    @Column
    private String ingredient;

}
