package com.twentyfoura.menuapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.twentyfoura.menuapp.model.CrudResponse;
import com.twentyfoura.menuapp.model.Ingredient;
import com.twentyfoura.menuapp.service.IngredientManagerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class IngredientControllerTest {

    @MockBean
    public IngredientManagerService ingredientManagerService;
    @MockBean
    public Logger logger;
    @Autowired
    private WebApplicationContext webAppContext;
    @Mock
    public MockMvc mockMvc;

    @Test
    public void createIngredient_IngredientIsCreatedHappyPath() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        String endpoint = "/v1/menuapp/createingredient";
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("pepper");
        Mockito.when(ingredientManagerService.createIngredient(anyString())).thenReturn("created pepper");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ingredient);
        String response = "";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = result.getResponse();
        response = mockHttpServletResponse.getContentAsString();
        byte[] jsonData = response.getBytes();
        ObjectMapper mapper = new ObjectMapper();
        CrudResponse crudResponse = mapper.readValue(jsonData, CrudResponse.class);
        assert(crudResponse.getMessage().equals("created pepper"));
    }

}
