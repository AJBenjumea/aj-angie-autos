package com.automobile.autodealer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autos")
public class AutosController {
    AutoDataService autoDataService;

    public AutosController(AutoDataService autoDataService) {
        this.autoDataService = autoDataService;
    }

    @GetMapping()
    public ResponseEntity<Automobiles> getAutos () {

        return autoDataService.getAutos().getAutomobiles().size() == 0 ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(autoDataService.getAutos());
    }

    @GetMapping("/{vin}")
    public Auto getAutoByVin(@PathVariable String vin) {
        return autoDataService.getAutoByVin(vin);
    }

    @PostMapping()
    public Auto addAuto(@RequestBody Auto auto) {
        return autoDataService.addAuto(auto);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoExceptionHandler(InvalidAutoException e) {
    }

}
