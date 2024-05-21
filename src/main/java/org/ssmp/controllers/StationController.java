package org.ssmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Station;
import org.ssmp.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;


    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/all")
    public List<Station> stationList(){
        return stationService.getStationsList();
    }
}
