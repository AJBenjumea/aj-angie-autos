package com.automobile.autodealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AutosControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutoDataService autoDataService;

    List<Auto> autoList;

    @BeforeEach
    void setUp() {
        autoList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            autoList.add(new Auto("12" + i));
        }
    }

    @Test
    void getAutos_noParams_exists_returnsAutoList() throws Exception {
        when(autoDataService.getAutos()).thenReturn(new Automobiles(autoList));

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(10)));
    }

    @Test
    void getAutos_noParams_noneExist_returnsNoContent() throws Exception {
        when(autoDataService.getAutos()).thenReturn(new Automobiles(new ArrayList<>()));

        mockMvc.perform(get("/api/autos"))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAutoByVin() throws Exception {
        when(autoDataService.getAutoByVin("123")).thenReturn(autoList.get(2));

        mockMvc.perform(get("/autos/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value("123"));
    }

    @Test
//    need to refactor this test to incorporate year, make, model and consider the content type on line 67
    void addAuto() throws  Exception{
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");

        when(autoDataService.addAuto(any(Auto.class))).thenReturn(auto);

        mockMvc.perform(post("/autos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"vin\":\"abc\", " +
                            "\"year\":2014," +
                            "\"make\":\"Acura\"," +
                            "\"model\":\"Integra\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value("abc"));

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
