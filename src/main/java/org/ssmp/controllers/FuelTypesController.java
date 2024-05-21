package org.ssmp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.FuelTypes;
import org.ssmp.service.FuelTypesService;

import java.util.List;

@RestController
@RequestMapping("/fuel-types")
@CrossOrigin("http://localhost:5173/")
public class FuelTypesController {

    private final FuelTypesService fuelTypesService;

    public FuelTypesController(FuelTypesService fuelTypesService) {
        this.fuelTypesService = fuelTypesService;
    }

    @GetMapping("/all")
    public List<FuelTypes> fuelTypesList(){
        return fuelTypesService.getFuelTypesList();
    }
}
