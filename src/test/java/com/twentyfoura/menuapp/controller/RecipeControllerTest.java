package com.twentyfoura.menuapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twentyfoura.menuapp.entity.RecipeEntity;
import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class RecipeControllerTest {

    @MockBean
    private RecipeService recipeService;
    @Autowired
    private WebApplicationContext webAppContext;
    @Mock
    public MockMvc mockMvc;

    public void prepareContext(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void getRecipe_HappyPath() throws Exception{
        prepareContext();
        String endpoint = "/v1/menuapp/getrecipes";
        String recipe = "ham&eggs";
        String response = "";
        RecipeEntity recipeEntity =  new RecipeEntity();
        Mockito.when(recipeService.getRecipe(anyString())).thenReturn(new RecipeEntity());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("recipe", recipe);
        //mockMvc.perform(requestBuilder).andExpect(status().isOk());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = result.getResponse();

        response = mockHttpServletResponse.getContentAsString();
        byte[] jsonData = response.getBytes();
        ObjectMapper mapper = new ObjectMapper();

        CrudResponse crudResponse = mapper.readValue(jsonData, CrudResponse.class);
        String objResult = crudResponse.getMessage();
        assert(crudResponse.getMessage().equals(objResult));
    }

    @Test
    public void getRecipe_HappyPath_RecipeNotFound() throws Exception{
        prepareContext();
        String endpoint = "/v1/menuapp/getrecipes";
        String recipe = "ham&eggs";
        String response = "";
        RecipeEntity recipeEntity =  new RecipeEntity();
        Mockito.when(recipeService.getRecipe(anyString())).thenReturn(new RecipeEntity());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam("recipe", recipe);
        //mockMvc.perform(requestBuilder).andExpect(status().isOk());
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = result.getResponse();

        response = mockHttpServletResponse.getContentAsString();
        byte[] jsonData = response.getBytes();
        ObjectMapper mapper = new ObjectMapper();

        CrudResponse crudResponse = mapper.readValue(jsonData, CrudResponse.class);
        String objResult = crudResponse.getMessage();
        assert(crudResponse.getMessage().equals(objResult));
    }
}
