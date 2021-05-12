package com.automobile.autodealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
        when(autosRepository.findAll()).thenReturn(Arrays.asList(auto));
        Automobiles automobiles = autoDataService.getAutos();
        assertThat(automobiles).isNotNull();
        assertThat(automobiles.isEmpty()).isFalse();
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