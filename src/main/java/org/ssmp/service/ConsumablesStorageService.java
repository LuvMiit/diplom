package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.dtos.ConsumStorageDTO;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.repository.ConsumablesStorageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumablesStorageService {

    private final ConsumablesStorageRepository consumablesStorageRepository;
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
}
