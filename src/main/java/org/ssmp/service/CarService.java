package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.ssmp.Utils.HibernateUtil;
import org.ssmp.dtos.CarPostDTO;
import org.ssmp.dtos.ChangeCarInfoDTO;
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
        carSMP.setBrand(car.getBrand());
        carSMP.setYearRelease(car.getRelease());
        carSMP.setDateStart(car.getDateStart());
        System.out.println(carSMP);
        carRepository.save(carSMP);

    }

    public void writeDownCar(String carPlates){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            CarSMP carSMP = carRepository.findByCarPlates(carPlates);
            session.evict(carSMP);
            carSMP.setStatus(statusService.getByStatusName("Архив"));
            session.merge(carSMP);
            tr.commit();
        }
    }

    public void changeCarInfo(ChangeCarInfoDTO changeCarInfoDTO){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            Transaction tr = session.beginTransaction();
            CarSMP carSMP = carRepository.findByCarPlates(changeCarInfoDTO.getOldCarPlates());
            session.evict(carSMP);

            if(!changeCarInfoDTO.getNewCarPlates().isEmpty()){
                carSMP.setCarPlates(changeCarInfoDTO.getNewCarPlates());
            }
            if(!changeCarInfoDTO.getFuel().isEmpty()){
                carSMP.setFuelType(fuelTypesService.getFuelByName(changeCarInfoDTO.getFuel()));
            }
            if(!changeCarInfoDTO.getType().isEmpty()){
                carSMP.setType(typesService.getByTypeName(changeCarInfoDTO.getType()));
            }
            if(!changeCarInfoDTO.getStatus().isEmpty()){
                carSMP.setStatus(statusService.getByStatusName(changeCarInfoDTO.getStatus()));
            }
            if(!changeCarInfoDTO.getMileage().isEmpty()){
                carSMP.setMileage(Double.parseDouble(changeCarInfoDTO.getMileage()));
            }
            if(!changeCarInfoDTO.getVinNumber().isEmpty()){
                carSMP.setVinNumber(changeCarInfoDTO.getVinNumber());
            }
            if(!changeCarInfoDTO.getBrand().isEmpty()){
                carSMP.setBrand(changeCarInfoDTO.getBrand());
            }
            if(!changeCarInfoDTO.getRelease().isEmpty()){
                carSMP.setYearRelease(changeCarInfoDTO.getRelease());
            }
            if(!changeCarInfoDTO.getDateStart().isEmpty()){
                carSMP.setDateStart(changeCarInfoDTO.getDateStart());
            }
            session.merge(carSMP);
            tr.commit();
        }
    }

}
