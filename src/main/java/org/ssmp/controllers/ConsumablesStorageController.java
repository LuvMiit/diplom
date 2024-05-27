package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.ConsumStorageDTO;
import org.ssmp.dtos.ConsumableSaveDTO;
import org.ssmp.dtos.ConsumableStorageSaveDTO;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.service.ConsumablesStorageService;

import java.util.List;

@RestController
@RequestMapping("/consum-storage")
@RequiredArgsConstructor
@CrossOrigin
public class ConsumablesStorageController {

    private final ConsumablesStorageService consumablesStorageService;

    @GetMapping("/all/{address}")
    public List<ConsumStorageDTO> consumablesStorageList(@PathVariable("address") String address) {
       return consumablesStorageService.getConsumablesStorageListByStorage(address);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveConsumable(@RequestBody ConsumableStorageSaveDTO consumableDTO){
        try {
            consumablesStorageService.updateConsumablesQuantity(consumableDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println("Нет сюда "+e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
