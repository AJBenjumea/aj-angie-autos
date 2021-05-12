package com.automobile.autodealer;

import org.springframework.stereotype.Service;

@Service
public class AutoDataService {
    //List<Auto> autoDataList = new ArrayList<>();
    //Automobiles autoDataList = new Automobiles(new ArrayList<>());

    AutosRepository autosRepository;

    public AutoDataService(AutosRepository autosRepository) {
        this.autosRepository = autosRepository;
    }

    public Automobiles getAutos() {
        return new Automobiles(autosRepository.findAll());
    }

    public Auto getAutoByVin(String vin) {
        return autosRepository.findByVinContains(vin).orElse(null);
    }

    public Auto addAuto(Auto auto) {
        return autosRepository.save(auto);
    }

    public Auto updateAuto(String vin, String color, String owner) {
        return null;
    }

    public void deleteAuto(String anyString) {
    }
}
