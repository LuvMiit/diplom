package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.ssmp.Utils.HibernateUtil;
import org.ssmp.dtos.CrewResponseDTO;
import org.ssmp.dtos.CrewSaveDTO;
import org.ssmp.model.Crew;
import org.ssmp.repository.CrewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrewService {

    private final CrewRepository crewRepository;
    private final StaffService staffService;
    private final CarService carService;

    public List<CrewResponseDTO> getCrewList(){
        List<Crew> crewList = crewRepository.findAll();
        List<CrewResponseDTO> crewResponseDTOList = new ArrayList<>();
        for(Crew crew:crewList){
            CrewResponseDTO crewResponseDTO = new CrewResponseDTO();
            crewResponseDTO.setCrewNumber(String.valueOf(crew.getCrewID()));
            crewResponseDTO.setCarPlates(crew.getCarSMP().getCarPlates());
            crewResponseDTO.setDriver(crew.getDriver().getWorkerID()+" "+staffService.getFIO(crew.getDriver()));
            crewResponseDTOList.add(crewResponseDTO);
        }
        return crewResponseDTOList;
    }

    public boolean saveCrew(CrewSaveDTO crewSaveDTO){
        Crew crew = new Crew();
        crew.setDriver(staffService.findById(getDriverID(crewSaveDTO.getDriver())));
        crew.setCarSMP(carService.findCarByPlates(crewSaveDTO.getCarPlates()));

        if(isCrewExist(crew)){
            return false;
        }
        crewRepository.save(crew);
        return true;
    }
    private boolean isCrewExist(Crew crew){
        return crewRepository.findByDriver(crew.getDriver()) != null;
    }
    private long getDriverID(String driver){
        String[] parts = driver.split(" ");
        return Long.parseLong(parts[0]);
    }
}
