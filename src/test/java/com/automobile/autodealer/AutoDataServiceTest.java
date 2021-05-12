package com.automobile.autodealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AutoDataServiceTest {
    private AutoDataService autoDataService;

    @Mock
    AutosRepository autosRepository;
    
    @BeforeEach
    void setUp() {
        autoDataService = new AutoDataService(autosRepository);
    }

    @Test
    void getAutos() {
        Automobiles automobiles = autoDataService.getAutos();
        assertThat(automobiles).isNotNull();
    }

    @Test
    void getAutoByVin() {
    }

    @Test
    void addAuto() {
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}