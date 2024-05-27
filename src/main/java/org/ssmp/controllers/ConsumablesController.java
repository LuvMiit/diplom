package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.ConsumableSaveDTO;
import org.ssmp.model.Consumables;
import org.ssmp.service.ConsumableService;

import java.util.List;

@RestController
@RequestMapping("/consumable")
@RequiredArgsConstructor
@CrossOrigin
public class ConsumablesController {

    private final ConsumableService consumableService;
    @GetMapping("/all")
    public List<Consumables> consumablesList(){
        return consumableService.getConsumablesList();
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveNewConsumable(@RequestBody ConsumableSaveDTO consumableSaveDTO){
        System.out.println(consumableSaveDTO);
        if(consumableService.getConsumableByName(consumableSaveDTO.getConsumableName()) == null){
            consumableService.saveConsumable(consumableSaveDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
