package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.dtos.CarPostDTO;
import org.ssmp.model.*;
import org.ssmp.repository.CarRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final StationService stationService;
    private final FuelTypesService fuelTypesService;
    private final TypesService typesService;
    private final StatusService statusService;

    public List<CarSMP> getCarList(){
        return carRepository.findAll();
    }
    public boolean isCarExist(String carPlates){
        return carRepository.findByCarPlates(carPlates) != null;
    }
    public CarSMP findCarByPlates(String carPlates){
        return carRepository.findByCarPlates(carPlates);
    }
    public void addCar(CarPostDTO car){

        CarSMP carSMP = new CarSMP();
        carSMP.setStation(stationService.getStationByName(car.getStationName()));
        carSMP.setVinNumber(car.getVin());
        carSMP.setCarPlates(car.getCarPlates());
        carSMP.setFuelType(fuelTypesService.getFuelByName(car.getFuel()));
        carSMP.setMileage(car.getMileage());
        carSMP.setType(typesService.getByTypeName(car.getType()));
        carSMP.setStatus(statusService.getByStatusName(car.getStatus()));
        System.out.println(carSMP);
        carRepository.save(carSMP);

    }

}
