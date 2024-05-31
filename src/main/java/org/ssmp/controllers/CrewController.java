package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.CrewResponseDTO;
import org.ssmp.dtos.CrewSaveDTO;
import org.ssmp.model.Crew;
import org.ssmp.service.CrewService;

import java.util.List;

@RestController
@RequestMapping("/crew")
@CrossOrigin
@RequiredArgsConstructor
public class CrewController {

    private final CrewService crewService;

    @GetMapping("/all")
    public List<CrewResponseDTO> crewList(){
        return crewService.getCrewList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveCrew(
            @RequestBody CrewSaveDTO crewSaveDTO
    ){

            if(crewService.saveCrew(crewSaveDTO)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);

    }}
