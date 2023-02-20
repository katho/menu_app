package com.twentyfoura.menuapp.service;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import com.twentyfoura.menuapp.repository.IngredientsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientManagerService {
    @Autowired
    public IngredientsRepository ingredientsRepository;

    public IngredientManagerService(){}

    public String createIngredient(String ingredient){
        Optional<IngredientEntity> ingredientEntity = ingredientsRepository.getIngredientByIngredient(ingredient);
        IngredientEntity ingredientEntity1 = new IngredientEntity();

        if(!ingredientEntity.isPresent()){
            IngredientEntity ingredientEntityToSave = new IngredientEntity();
            ingredientEntityToSave.setIngredient(ingredient);
            ingredientsRepository.save(ingredientEntityToSave);
            return "created "+ingredient;
        }

        return "ingredient already exists!";
    }

    public String getIngredient(String ingredient){
        if(StringUtils.isBlank(ingredient)){return "Please enter a valid ingredient!";}
        Optional<IngredientEntity> ingredientEntity = ingredientsRepository.getIngredientByIngredient(ingredient);
        if(!ingredientEntity.isPresent()){
            return "Ingredient does not exists!";
        }
        return "Ingredient exists!";
    }

}
