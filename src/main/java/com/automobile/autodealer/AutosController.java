package com.automobile.autodealer;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{vin}")
    public Auto getAutoByVin(@PathVariable String vin) {
        return autoDataService.getAutoByVin(vin);
    }

    @PostMapping()
    public Auto addAuto(@RequestBody Auto auto) {
        return autoDataService.addAuto(auto);
    }
}
