package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Consumables;
import org.ssmp.repository.ConsumablesRepository;

import java.util.List;

@Service
public class ConsumableService {
    private final ConsumablesRepository consumablesRepository;

    public ConsumableService(ConsumablesRepository consumablesRepository) {
        this.consumablesRepository = consumablesRepository;
    }
    public List<Consumables> getConsumablesList(){
        return consumablesRepository.findAll();
    }
}
