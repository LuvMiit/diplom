package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Crew;
import org.ssmp.repository.CrewRepository;

import java.util.List;

@Service
public class CrewService {

    private final CrewRepository crewRepository;

    public CrewService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Crew> getCrewList(){
        return crewRepository.findAll();
    }
}
