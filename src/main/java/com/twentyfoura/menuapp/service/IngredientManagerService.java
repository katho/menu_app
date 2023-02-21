package com.twentyfoura.menuapp.service;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import com.twentyfoura.menuapp.repository.IngredientsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public String createIngredientByList(List<String> list){
        Optional<IngredientEntity> ingredientEntity;
        IngredientEntity ingredientEntity1 = new IngredientEntity();
        HashMap<String, String> myHashMap =  new HashMap<>();
        for(int x = 0; x<list.size(); x++){
            ingredientEntity = ingredientsRepository.getIngredientByIngredient(list.get(x));
            if(!ingredientEntity.isPresent()){
                ingredientEntity1.setIngredient(list.get(x));
                ingredientsRepository.save(ingredientEntity1);
                myHashMap.put(ingredientEntity1.getIngredient(), "saved");
            }else {
                ingredientEntity1 =  ingredientEntity.get();
                myHashMap.put(ingredientEntity1.getIngredient(), "duplicated, not saved");
            }
        }

        return "process finished: "+myHashMap.toString();
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
