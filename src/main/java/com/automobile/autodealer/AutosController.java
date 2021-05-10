package com.automobile.autodealer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/autos")
public class AutosController {
    AutoDataService autoDataService;

    public AutosController(AutoDataService autoDataService) {
        this.autoDataService = autoDataService;
    }

    @GetMapping()
    public List<Auto>  getAutos () {
        return autoDataService.getAutos();
    }

}
