package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.ImageType;
import org.ssmp.repository.ImageTypesRepository;

@Service
@RequiredArgsConstructor
public class ImageTypesService {

    private final ImageTypesRepository imageTypesRepository;

    public ImageType getImageType(String name){
        return imageTypesRepository.findByTypeName(name);
    }
}
