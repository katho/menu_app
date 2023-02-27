package com.twentyfoura.menuapp.service;

import com.twentyfoura.menuapp.entity.RecipeEntity;
import com.twentyfoura.menuapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("recipeservice")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public RecipeEntity getRecipe(@Param("recipe") String recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();
        Optional<RecipeEntity> recipeEntityOptional;
        recipeEntityOptional = recipeRepository.getRecipeByName(recipe);

        if(!recipeEntityOptional.isPresent()){
            return recipeEntity;
        }

        return recipeEntityOptional.get();
    }
}
