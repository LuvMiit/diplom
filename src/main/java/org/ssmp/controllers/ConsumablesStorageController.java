package org.ssmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.service.ConsumablesStorageService;

import java.util.List;

@RestController
@RequestMapping("/consum-storage")
public class ConsumablesStorageController {

    private final ConsumablesStorageService consumablesStorageService;


    public ConsumablesStorageController(ConsumablesStorageService consumablesStorageService) {
        this.consumablesStorageService = consumablesStorageService;
    }

    @GetMapping("/all")
    public List<ConsumablesStorage> consumablesStorageList(){
       return consumablesStorageService.getConsumablesStorageList();
    }
}
