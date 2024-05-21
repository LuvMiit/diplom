package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.Station;
import org.ssmp.repository.StationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    public List<Station> getStationsList(){

        return stationRepository.findAll();

    }

    public Station getStationByName(String stationName){
        return stationRepository.findByStationName(stationName);
    }

}
