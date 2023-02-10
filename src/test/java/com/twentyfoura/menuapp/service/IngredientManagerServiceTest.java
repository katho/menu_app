package com.twentyfoura.menuapp.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class IngredientManagerServiceTest {

    @Autowired
    public IngredientManagerService ingredientManagerService;

    @Test
    public void createIngredient_HappyPath() throws Exception{
        String ingredient = "frijoles";
        assertEquals(ingredientManagerService.createIngredient(ingredient), "created "+ingredient);
    }

    @Test
    public void createIngredient_HappyPath_ItemExists() throws Exception{
        String ingredient = "cebolla";
        String result = "ingredient already exists!";
        assertEquals(ingredientManagerService.createIngredient(ingredient), result);
    }


    /**
     * Primer fallo: el ingrediente null se creó
     * @throws Exception
     */
    @Test
    public void createIngredient_NullString() throws Exception{
        String ingredient = null;
        String result = "ingredient already exists!";
        assertEquals(ingredientManagerService.createIngredient(ingredient), null);
    }

    /**
     * Segundo fallo: la cadena tiene números
     * @throws Exception
     */
    @Test
    public void createIngredient_StringWithNumbers() throws Exception{
        String ingredient = "123456";
        String result = "ingredient already exists!";
        assertEquals(ingredientManagerService.createIngredient(ingredient), null);
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

}
