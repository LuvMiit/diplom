package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.ConsumStorageDTO;
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
}
