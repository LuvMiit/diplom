package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Storage;
import org.ssmp.repository.StorageRepository;

import java.util.List;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> getStorageList(){
        return storageRepository.findAll();
    }
}
