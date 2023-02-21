package com.twentyfoura.menuapp.controller;

import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.model.Ingredient;
import com.twentyfoura.menuapp.model.IngredientList;
import com.twentyfoura.menuapp.service.IngredientManagerService;
import javafx.scene.effect.Bloom;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IngredientController {
    public Logger logger = LoggerFactory.getLogger(IngredientController.class);
    @Autowired
    public IngredientManagerService ingredientManagerService;

    @PostMapping("v1/menuapp/createingredient")
    public ResponseEntity<CrudResponse> getIngredient(@RequestBody Ingredient ingredient){

        String regex = "[a-z][A-Z] ";
        CrudResponse crudResponse = new CrudResponse();
        if(StringUtils.isBlank(ingredient.getIngredient())){
            crudResponse.setMessage("Please enter a valid ingredient!");
            return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.BAD_REQUEST);
        }
        else{
            logger.info(ingredient.getIngredient());
            String response = ingredientManagerService.createIngredient(ingredient.getIngredient());
            crudResponse.setMessage(response);
            return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
        }

    }

    @PostMapping("v1/menuapp/createingredientbylist")
    public ResponseEntity<CrudResponse> getIngredientByList(@RequestBody IngredientList ingredientlist){
        CrudResponse crudResponse = new CrudResponse();
        logger.info(ingredientlist.toString());
        List<String> myList = new ArrayList<>();
        for(Ingredient ingredient: ingredientlist.getIngredientList()){
            if(!StringUtils.isBlank(ingredient.getIngredient())){
                myList.add(ingredient.getIngredient());
            }
        }
        if(myList.isEmpty()){
            crudResponse.setMessage("List of ingredients not valid!");
            return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.BAD_REQUEST);
        }
        crudResponse.setMessage(ingredientManagerService.createIngredientByList(myList));
        return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
    }

    @GetMapping("v1/menuapp/getingredient")
    public ResponseEntity<CrudResponse> getIngredient(@Param("ingredient") String ingredient){
        logger.info(ingredient);
        CrudResponse crudResponse = new CrudResponse();
        if(StringUtils.isBlank(ingredient)){
            crudResponse.setMessage("Please enter a valid ingredient!");
            return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.BAD_REQUEST);
        }
        String response = ingredientManagerService.getIngredient(ingredient);
        crudResponse.setMessage(response);
        return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
    }

}
