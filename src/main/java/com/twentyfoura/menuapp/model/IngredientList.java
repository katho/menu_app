package com.twentyfoura.menuapp.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class IngredientList {
    private List<Ingredient> ingredientList;
}
