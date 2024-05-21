package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.Utils.ImageUtil;
import org.ssmp.model.CarSMP;
import org.ssmp.model.Images;
import org.ssmp.repository.ImageRepository;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile file) throws IOException {
        Images imageData = imageRepository.save(Images.builder()
                .imageName(file.getOriginalFilename())
                .typeImage(file.getContentType())
                .dataImage(ImageUtil.compressImage(file.getBytes())).build());

        if(imageData != null){
            return "file upload successfully "+file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(CarSMP carSMP){
//        Optional<Images> dbImage = imageRepository.findByCar(carSMP);
        Optional<Images> dbImage = imageRepository.findById((long)1);
        return ImageUtil.decompressImage(dbImage.get().getDataImage());

    }
}
