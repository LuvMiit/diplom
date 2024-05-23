package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
