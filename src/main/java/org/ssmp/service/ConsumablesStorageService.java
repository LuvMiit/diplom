package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.repository.ConsumablesStorageRepository;

import java.util.List;

@Service
public class ConsumablesStorageService {

    private final ConsumablesStorageRepository consumablesStorageRepository;

    public ConsumablesStorageService(ConsumablesStorageRepository consumablesStorageRepository) {
        this.consumablesStorageRepository = consumablesStorageRepository;
    }

    public List<ConsumablesStorage> getConsumablesStorageList(){
        return consumablesStorageRepository.findAll();
    }
}
