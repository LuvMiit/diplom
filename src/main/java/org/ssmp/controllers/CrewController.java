package org.ssmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Crew;
import org.ssmp.service.CrewService;

import java.util.List;

@RestController
@RequestMapping("/crew")
public class CrewController {

    private final CrewService crewService;

    public CrewController(CrewService crewService) {
        this.crewService = crewService;
    }

    @GetMapping("/all")
    public List<Crew> crewList(){
        return crewService.getCrewList();
    }
}
