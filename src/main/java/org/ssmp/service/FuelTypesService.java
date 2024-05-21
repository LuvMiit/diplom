package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.FuelTypes;
import org.ssmp.repository.FuelTypesRepository;

import java.util.List;

@Service
public class FuelTypesService {

    private final FuelTypesRepository fuelTypesRepository;


    public FuelTypesService(FuelTypesRepository fuelTypesRepository) {
        this.fuelTypesRepository = fuelTypesRepository;
    }

    public List<FuelTypes> getFuelTypesList(){
        return fuelTypesRepository.findAll();
    }

    public FuelTypes getFuelByName(String fuelName){
        return fuelTypesRepository.findByFuelName(fuelName);
    }
}
