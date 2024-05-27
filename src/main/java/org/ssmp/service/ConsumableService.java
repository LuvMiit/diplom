package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.dtos.ConsumableSaveDTO;
import org.ssmp.model.Consumables;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.model.Storage;
import org.ssmp.repository.ConsumablesRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumableService {
    private final ConsumablesRepository consumablesRepository;
    public List<Consumables> getConsumablesList(){
        return consumablesRepository.findAll();
    }

    public Consumables getConsumableByName(String name){
        return consumablesRepository.findByConsumableName(name);
    }
    public void saveConsumable(ConsumableSaveDTO consumableDTO){
        Consumables consumable = new Consumables();
        consumable.setConsumableName(consumableDTO.getConsumableName());
        consumablesRepository.save(consumable);
    }

}
