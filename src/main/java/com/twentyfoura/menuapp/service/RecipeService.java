package com.twentyfoura.menuapp.service;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import com.twentyfoura.menuapp.entity.RecipeEntity;
import com.twentyfoura.menuapp.filter.RequestResponseFilter;
import com.twentyfoura.menuapp.repository.IngredientsRepository;
import com.twentyfoura.menuapp.repository.RecipeRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("recipeservice")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientsRepository ingredientsRepository;

    public Logger logger = LoggerFactory.getLogger(RecipeService.class);

    public RecipeEntity getRecipe( String recipe) {
        RecipeEntity recipeEntity = new RecipeEntity();
        Optional<RecipeEntity> recipeEntityOptional;
        recipeEntityOptional = recipeRepository.getRecipeByName(recipe);

        if(!recipeEntityOptional.isPresent()){
            logger.info("Recipe : "+recipeEntity.toString());
            return recipeEntity;
        }

        return recipeEntityOptional.get();
    }

    public RecipeEntity createRecipe(RecipeEntity recipeEntity) {
        Optional<RecipeEntity> recipeEntityOptional;
        recipeEntityOptional = recipeRepository.getRecipeByName(recipeEntity.getName());
        Optional<IngredientEntity> ingredient;
        List<IngredientEntity> ingredientEntityList = recipeEntity.getIngredient();
        Optional<IngredientEntity> ingredientEntityOptional;
        for(IngredientEntity item: ingredientEntityList){
            if(StringUtils.isBlank(item.getIngredient())){
                return new RecipeEntity();
            }
        }
        for(IngredientEntity entity: ingredientEntityList){
            ingredient = ingredientsRepository.getIngredientByIngredient(entity.getIngredient());
            if(!ingredient.isPresent()){
                ingredientsRepository.save(entity);
            }
        }
        ingredientEntityList = new ArrayList<>();
        for(IngredientEntity entity: ingredientEntityList){
            ingredient = ingredientsRepository.getIngredientByIngredient(entity.getIngredient());
            if(!ingredient.isPresent()){
                ingredientEntityList.add(ingredient.get());
            }
        }
        logger.info("Ingredient list: "+ingredientEntityList.toString());
        recipeEntity.setIngredient(ingredientEntityList);


        if(!recipeEntityOptional.isPresent()){
            recipeRepository.save(recipeEntity);
            return recipeEntity;
        }

        return recipeEntity;
    }
}
