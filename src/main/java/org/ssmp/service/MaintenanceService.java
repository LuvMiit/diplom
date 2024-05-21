package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.ssmp.Utils.HibernateUtil;
import org.ssmp.dtos.MaintenanceDTO;
import org.ssmp.model.CarSMP;
import org.ssmp.model.Maintenance;
import org.ssmp.repository.MaintenanceRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final CarService carService;

    public List<Maintenance> getMaintenanceList(){return maintenanceRepository.findAll();}

    public List<MaintenanceDTO> getMaintenancesByCarPlates(String carPlates){
        CarSMP car = carService.findCarByPlates(carPlates);
        System.out.println();
        List<MaintenanceDTO> maintenanceDTOList = new ArrayList<>();
        for(Maintenance maintenance:maintenanceRepository.findAllByCarSMP(car)) {
            MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
            maintenanceDTO.setMaintenanceID(maintenance.getMaintenanceID());
            maintenanceDTO.setCarPlates(maintenance.getCarSMP().getCarPlates());
            maintenanceDTO.setDate(maintenance.getDate());
            maintenanceDTO.setEmployeeID(maintenance.getWorker().getWorkerID());
            maintenanceDTO.setFirstName(maintenance.getWorker().getFirstname());
            maintenanceDTO.setSurName(maintenance.getWorker().getSurname());
            maintenanceDTO.setPatronimyc(maintenance.getWorker().getPatronymic());
            maintenanceDTO.setUsedOperations(maintenance.getOperationsInMaintenanceList());
            maintenanceDTO.setUsedConsumables(maintenance.getConsumablesInMaintenanceList());
            maintenanceDTOList.add(maintenanceDTO);
        }
        return maintenanceDTOList;
    }
}
