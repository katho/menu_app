package com.twentyfoura.menuapp.service;

import com.twentyfoura.menuapp.entity.IngredientEntity;
import com.twentyfoura.menuapp.repository.IngredientsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class IngredientManagerServiceTest {

    @Autowired
    public IngredientManagerService ingredientManagerService;
    @MockBean
    public IngredientsRepository repository;

    @Test
    public void createIngredient_HappyPath() throws Exception{
        String ingredient = "rice";
        Optional<IngredientEntity> ingredientEntity;
        ingredientEntity = repository.getIngredientByIngredient(anyString());
        Mockito.when(ingredientManagerService.createIngredient(anyString())).thenReturn("created "+ingredient);
        assert(!ingredientEntity.isPresent());
    }

    @Test
    public void createIngredient_HappyPath_ItemExists() throws Exception{
        String ingredient = "cebolla";
        Optional<IngredientEntity> ingredientEntity;
        ingredientEntity = repository.getIngredientByIngredient(anyString());
        IngredientEntity entity = new IngredientEntity();
        entity.setIngredient(ingredient);
        ingredientEntity = Optional.of(entity);
        //Mockito.when(ingredientManagerService.createIngredient(anyString())).thenReturn("created "+ingredient);
        assertEquals(ingredientManagerService.createIngredient(ingredient), "Ingredient exists!");
    }

    @Test
    public void getIngredient_HappyPath_IngredientExists() throws Exception{
        String ingredient = "cebolla";
        String result = ingredientManagerService.getIngredient(ingredient);
        assertEquals(result, "Ingredient exists!");
    }

    @Test
    public void getIngredient_HappyPath_IngredientDoesNotExists() throws Exception{
        String ingredient = "sal del himalaya";
        String result = ingredientManagerService.getIngredient(ingredient);
        assertEquals(result, "Ingredient does not exists!");
    }

    @Test
    public void getIngredient_HappyPath_IngredientStringNullBlankWhiteSpace() throws Exception{
        String ingredient = null;
        String result = ingredientManagerService.getIngredient(ingredient);
        assertEquals(result, "Please enter a valid ingredient!");
    }

}
