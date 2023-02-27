package com.twentyfoura.menuapp.controller;

import com.twentyfoura.menuapp.entity.RecipeEntity;
import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.service.RecipeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

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


}
