package com.automobile.autodealer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoDataService {
    List<Auto> autoDataList = new ArrayList<>();
    //Automobiles autoDataList = new Automobiles(new ArrayList<>());

    public Automobiles getAutos() {
//        return autoDataList;
        return null;
    }

    public Auto getAutoByVin(String vin) {
        for(Auto auto : autoDataList) {
            if (auto.getVin() == vin) {
                return auto;
            }
        }
        return null;
    }

    public Auto addAuto(Auto auto) {
//        autoDataList.add(auto);
//        return auto;
        return null;
    }

    public Auto updateAuto(String vin, String color, String owner) {
        return null;
    }

    public void deleteAuto(String anyString) {
    }
}
