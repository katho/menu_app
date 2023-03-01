package com.twentyfoura.menuapp.controller;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import com.twentyfoura.menuapp.entity.RecipeEntity;
import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    public Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @GetMapping("v1/menuapp/getrecipes")
    public ResponseEntity<CrudResponse> getRecipes(@Param("recipe") String recipe){
        CrudResponse crudResponse =  new CrudResponse();
        if(StringUtils.isBlank(recipe)){
            crudResponse.setMessage("Please enter a valid name for recipe!");
            return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
        }

        RecipeEntity recipeEntity = recipeService.getRecipe(recipe);
        crudResponse.setMessage(recipeEntity.toString());

        return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
    }

    @PostMapping("v1/menuapp/createrecipe")
    public ResponseEntity<CrudResponse>  creteRecipe(@RequestBody RecipeEntity recipeEntity){
        CrudResponse crudResponse =  new CrudResponse();
        String name = recipeEntity.getName();
        String description = recipeEntity.getDescription();
        List<IngredientEntity> ingredientList = recipeEntity.getIngredient();

        logger.info("DATA: "+recipeEntity);

        if(StringUtils.isBlank(name)){
            crudResponse.setMessage("Please enter a valid name!");
            return new ResponseEntity<CrudResponse>(crudResponse, HttpStatus.BAD_REQUEST);
        }else if(StringUtils.isBlank(description)){
            crudResponse.setMessage("Please enter a valid description!");
            return new ResponseEntity<CrudResponse>(crudResponse, HttpStatus.BAD_REQUEST);
        }
        for(IngredientEntity item: ingredientList){
            if(StringUtils.isBlank(item.getIngredient())){
                crudResponse.setMessage("Please enter a valid ingredient list");
                return new ResponseEntity<CrudResponse>(crudResponse, HttpStatus.BAD_REQUEST);
            }
        }
        RecipeEntity myRecipeEntity = new RecipeEntity();

        myRecipeEntity = recipeService.createRecipe(recipeEntity);



        crudResponse.setMessage(myRecipeEntity.toString());


        return new ResponseEntity<CrudResponse>(crudResponse, HttpStatus.OK);
    }


}
