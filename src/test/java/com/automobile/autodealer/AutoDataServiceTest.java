package com.automobile.autodealer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
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
    void getAutoByVin_whenExist_returnsAuto() {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
        when(autosRepository.findByVinContains(anyString())).thenReturn(Optional.of(auto));

        Auto foundAuto = autoDataService.getAutoByVin(auto.getVin());
        assertThat(foundAuto).isNotNull();
    }

//    @Test
//    void getAutoByVin_whenNoneExist_returnsAuto() {
//        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
//        when(autosRepository.findByVinContains(anyString())).thenReturn(null);
//
//        Auto foundAuto = autoDataService.getAutoByVin(auto.getVin());
//        assertThat(foundAuto).isNull();
//    }

    @Test
    void addAuto_valid_returnsAuto() {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
        when(autosRepository.save(any(Auto.class))).thenReturn(auto);

        Auto newAuto = autoDataService.addAuto(auto);
        assertThat(newAuto).isNotNull();
    }

//    @Test
//    void addAuto_invalid_returnsAuto() {
//        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
//        when(autosRepository.save(any(Auto.class))).thenThrow(InvalidAutoException.class);
//
//        Auto newAuto = autoDataService.addAuto(auto);
//        assertThat(newAuto).isInstanceOf(InvalidAutoException.class);
//    }

    @Test
    void updateAuto() {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
        when(autosRepository.findByVinContains(anyString())).thenReturn(Optional.of(auto));
        when(autosRepository.save(any(Auto.class))).thenReturn(auto);
        Auto updatedAuto = autoDataService.updateAuto(auto.getVin(), "BLUE", "Angie");
        assertThat(updatedAuto).isNotNull();
    }

    @Test
    void deleteAuto() {
        Auto auto = new Auto(2014, "Acura", "Integra", "abc");
        when(autosRepository.findByVinContains(anyString())).thenReturn(Optional.of(auto));
        autoDataService.deleteAuto(anyString());
        verify(autosRepository).delete(auto);
    }
}