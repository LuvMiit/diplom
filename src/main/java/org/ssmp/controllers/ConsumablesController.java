package org.ssmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Consumables;
import org.ssmp.service.ConsumableService;

import java.util.List;

@RestController
@RequestMapping("/consum")
public class ConsumablesController {

    private final ConsumableService consumableService;

    public ConsumablesController(ConsumableService consumableService) {
        this.consumableService = consumableService;
    }
    @GetMapping("/all")
    public List<Consumables> consumablesList(){
        return consumableService.getConsumablesList();
    }
}
