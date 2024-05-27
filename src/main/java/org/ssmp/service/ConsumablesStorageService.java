package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.dtos.ConsumStorageDTO;
import org.ssmp.dtos.ConsumableSaveDTO;
import org.ssmp.dtos.ConsumableStorageSaveDTO;
import org.ssmp.model.Consumables;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.model.Storage;
import org.ssmp.repository.ConsumablesStorageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumablesStorageService {

    private final ConsumablesStorageRepository consumablesStorageRepository;
    private final ConsumableService consumableService;
    private final StorageService storageService;

    public List<ConsumStorageDTO> getConsumablesStorageListByStorage(String address){

        List<ConsumablesStorage> consumablesStorage = consumablesStorageRepository
                .findAllByStorage(storageService.getStorageByAddress(address));
        List<ConsumStorageDTO> consumStorageDTOList = new ArrayList<>();
        for(ConsumablesStorage item: consumablesStorage){
           consumStorageDTOList.add(new ConsumStorageDTO(
                    item.getConsumable().getConsumableName(), item.getQuantity()
            ));

        }

        return consumStorageDTOList;
    }
    public void updateConsumablesQuantity(ConsumableStorageSaveDTO consumableDTO){
        Consumables consumable = consumableService.getConsumableByName(consumableDTO.getConsumableName());
        Storage storage = storageService.getStorageByAddress(consumableDTO.getStorageAddress());
        ConsumablesStorage consumablesStorage = consumablesStorageRepository.findByStorageAndConsumable(storage, consumable);

        if(consumablesStorage == null){
            saveNewEntry(consumableDTO, consumable, storage);
        }else {
            consumablesStorageRepository.save(new ConsumablesStorage(
                    consumablesStorage.getEntryID(), storage, consumable,
                    consumablesStorage.getQuantity() + consumableDTO.getQuantity()
            ));
        }
    }

    private void saveNewEntry(ConsumableStorageSaveDTO consumableDTO, Consumables consumable, Storage storage){
        ConsumablesStorage consumablesStorageNew = new ConsumablesStorage();
        consumablesStorageNew.setStorage(storage);
        consumablesStorageNew.setConsumable(consumable);
        consumablesStorageNew.setQuantity(consumableDTO.getQuantity());
        consumablesStorageRepository.save(consumablesStorageNew);
    }

}
