package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.Storage;
import org.ssmp.repository.StorageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;

    public List<Storage> getStorageList(){
        return storageRepository.findAll();
    }

    public Storage getStorageByAddress(String address){
        return storageRepository.findByStorageAddress(address);
    }
}
