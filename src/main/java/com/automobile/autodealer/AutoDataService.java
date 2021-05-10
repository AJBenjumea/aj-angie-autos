package com.automobile.autodealer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutoDataService {
    List<Auto> autoDataList = new ArrayList<>();

    public List<Auto> getAutos() {
        return autoDataList;
    }
}
