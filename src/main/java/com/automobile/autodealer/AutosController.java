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
    public ResponseEntity<Auto> getAutoByVin(@PathVariable String vin) {
        return autoDataService.getAutoByVin(vin) == null ?
                ResponseEntity.noContent().build() : ResponseEntity.ok(autoDataService.getAutoByVin(vin));
    }

    @PostMapping()
    public Auto addAuto(@RequestBody Auto auto) {
        return autoDataService.addAuto(auto);
    }

    @PatchMapping("/{vin}")
    public ResponseEntity<Auto> updateAutoByVin(@PathVariable String vin,
                                                @RequestBody UpdateAuto data) {
        //return autoDataService.updateAuto(vin, data.getColor(), data.getOwner());
         return autoDataService.getAutoByVin(vin) == null ?
                 ResponseEntity.noContent().build():
                 ResponseEntity.ok(autoDataService.updateAuto(vin , data.getColor(), data.getOwner()));
    }

    @DeleteMapping("/{vin}")
    public ResponseEntity deleteAuto(@PathVariable String vin) {
        try {
            autoDataService.deleteAuto(vin);
        } catch (InvalidAutoException e) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.accepted().build();
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void invalidAutoExceptionHandler(InvalidAutoException e) {
    }

}
