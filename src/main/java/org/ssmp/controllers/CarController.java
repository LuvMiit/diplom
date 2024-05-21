package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.Exceptions.ApplicationError;
import org.ssmp.dtos.CarPostDTO;
import org.ssmp.model.CarSMP;
import org.ssmp.repository.CarRepository;
import org.ssmp.repository.StationRepository;
import org.ssmp.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
@CrossOrigin("http://localhost:5173/")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final StationRepository stationRepository;


    @GetMapping("/all")
    public List<CarSMP> carSMPList(){
        return carService.getCarList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addCar(@RequestBody CarPostDTO car){
        if(carService.isCarExist(car.getCarPlates())){
            return new ResponseEntity<>(new ApplicationError("Такая машина уже существует",
                    HttpStatus.CONFLICT.value()),HttpStatus.CONFLICT);
        }
        carService.addCar(car);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
}
