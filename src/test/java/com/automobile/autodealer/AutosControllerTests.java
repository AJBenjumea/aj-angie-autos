package com.automobile.autodealer;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AutosControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutoDataService autoDataService;

    ObjectMapper mapper = new ObjectMapper();

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
    void getAuto_withVin_returnsAuto() throws Exception {
        when(autoDataService.getAutoByVin("123")).thenReturn(autoList.get(2));

        mockMvc.perform(get("/api/autos/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value("123"));
    }

    @Test
    void getAuto_nonExistentVin_returnsNotFound() throws Exception {
        when(autoDataService.getAutoByVin("doesNotExist")).thenReturn(null);
        mockMvc.perform(get("/api/autos/doesNotExist"))
                .andExpect(status().isNoContent());
    }

    @Test
    void addAuto_valid_returnsAuto() throws  Exception{
        // Arrange
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");

        when(autoDataService.addAuto(any(Auto.class))).thenReturn(auto);
        // Act
        mockMvc.perform(post("/api/autos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(auto)))
                //Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value("abc"));

    }

    @Test
    void addAuto_invalid_returnsNoContent() throws Exception{
        //Arrange
        when(autoDataService.addAuto(any(Auto.class))).thenThrow(InvalidAutoException.class);
        //Act
        mockMvc.perform(post("/api/autos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"message\":\"no way\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    void patchAuto_givenColorAndOwner_returnsUpdatedAuto() throws Exception {
        Auto auto = new Auto(2014, "Acura", "Integra", "Red", "AJ", "abc");

        when(autoDataService.updateAuto(anyString(), anyString(), anyString())).thenReturn(auto);
        when(autoDataService.getAutoByVin(anyString())).thenReturn(auto);

        mockMvc.perform(patch("/api/autos/abc")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"color\":\"Red\", \"owner\":\"AJ\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("color").value("Red"))
                .andExpect(jsonPath("owner").value("AJ"));
    }

    @Test
    void patchAuto_invalidVin_returnsNoContent() throws Exception {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");

        when(autoDataService.updateAuto(anyString(), anyString(), anyString())).thenReturn(auto);
        when(autoDataService.getAutoByVin(anyString())).thenReturn(null);

        mockMvc.perform(patch("/api/autos/abc")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"color\":\"Red\", \"owner\":\"AJ\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    void patchAuto_badRequest_returnsBadRequest() throws Exception {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");

        when(autoDataService.updateAuto(anyString(), anyString(), anyString())).thenReturn(auto);
        when(autoDataService.getAutoByVin(anyString())).thenThrow(InvalidAutoException.class);

        mockMvc.perform(patch("/api/autos/zzz")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\":\"no way\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteAuto_validVin_returnAccepted() throws Exception {

        mockMvc.perform(delete("/api/autos/abc"))
                .andExpect(status().isAccepted());
        verify(autoDataService).deleteAuto(anyString());
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
//      responses
//          200 - return a list of all autos (if any exist) as a JSON object has an automobiles key whose value is an array of all autos.
//          204 - no autos exist
// GET: /autos/{vin}
//      responses
//          200 - return an auto by its vin
//          204 - not found
// POST: /autos
//  request body - object same as schema
//      response
//          200 - automobile added successfully
//          400 - bad request
// PATCH: /autos/{vin}
//  request body: only update owner and color
//      response
//          200 - automobile added successfully
//          204 - invalid VIN
//          400 - bad request
// ******
// DELETE: /autos/{vin}
//      response
//    ***   202 - automobile delete request accepted
//          204 - Vehicle not found
