package com.twentyfoura.menuapp.controller;

import com.twentyfoura.menuapp.constants.ConstantsConfiguration;
import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.model.Ingredient;
import com.twentyfoura.menuapp.service.IngredientManagerService;
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

@Controller
public class IngredientController {
    final static Logger logger = LoggerFactory.getLogger(IngredientController.class);
    @Autowired
    public IngredientManagerService ingredientManagerService;

    @PostMapping("v1/menuapp/createingredient")
    public ResponseEntity<CrudResponse> getIngredient(@RequestBody Ingredient ingredient){
        logger.info(ingredient.getIngredient());
        String response = ingredientManagerService.createIngredient(ingredient.getIngredient());
        CrudResponse crudResponse = new CrudResponse();
        crudResponse.setMessage(response);
        return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
    }

    @GetMapping("v1/menuapp/getingredient")
    public ResponseEntity<CrudResponse> getIngredient(@Param("ingredient") String ingredient){
        logger.info(ingredient);
        String response = ingredientManagerService.getIngredient(ingredient);
        CrudResponse crudResponse = new CrudResponse();
        crudResponse.setMessage(response);
        return new ResponseEntity<CrudResponse>(crudResponse,HttpStatus.OK);
    }
}