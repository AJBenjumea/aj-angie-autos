package com.automobile.autodealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class autosControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    List<Auto> autoList;
    AutoDataService autoDataService;

    @BeforeEach
    void setUp() {
        autoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            autoList.add(new Auto());
        }
    }

    @Test
    void getAutos() throws Exception{
        when(autoDataService.getAutos()).thenReturn(autoList);

        mockMvc.perform(get("/autos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }
}

// Schema:
// int year
// String make
// string model
// String color
// String owner
// String vin
// GET: /autos
// responses
// 200 - return a list of all autos (if any exist) as a JSON object has an automobiles key whose value is an array of all autos.
// 204 - no autos exist
// GET: /autos/{vin}
// 200 - return an auto by its vin
// 204 - not found
//POST: /autos
// request body - object same as schema
// response
// 200 - automobile added successfully
// 400 - bad request
//PATCH: /autos/{vin}
// request body: only update owner and color
// response
// 200 - automobile added successfully
// 400 - bad request
//DELETE: /autos/{vin}
// response
// 202 - automobile delete request accepted
// 204 - Vehicle not found
