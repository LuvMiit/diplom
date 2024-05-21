package org.ssmp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.MaintenanceDTO;
import org.ssmp.model.Maintenance;
import org.ssmp.service.MaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@CrossOrigin
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/all")
    public List<Maintenance> maintenanceList(){
        return maintenanceService.getMaintenanceList();
    }

    @GetMapping("/{carPlates}")
    public List<MaintenanceDTO> getMaintenanceByCarPlates(@PathVariable("carPlates") String carPlates){
       return maintenanceService.getMaintenancesByCarPlates(carPlates);
    }
}
