package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.Utils.ImageUtil;
import org.ssmp.model.CarSMP;
import org.ssmp.model.ImageType;
import org.ssmp.model.Images;
import org.ssmp.repository.ImageRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String uploadImage(MultipartFile file, CarSMP car, ImageType type) throws IOException, ParseException {
        Date calendar =new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = String.valueOf(calendar);
        System.out.println(sdf.parse(sdf.format(calendar)));
        imageRepository.save(Images.builder()
                .imageName(file.getOriginalFilename())
                .typeImage(file.getContentType())
                .dataImage(ImageUtil.compressImage(file.getBytes()))
                .car(car)
                .createdDate(sdf.parse(sdf.format(calendar)))
                .type(type)
                .build());

        return "file upload successfully " + file.getOriginalFilename();
    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public byte[] downloadImage(CarSMP carSMP, ImageType type, Date date){
        Optional<Images> dbImage = imageRepository.findByCarAndTypeAndCreatedDate(carSMP, type, date);
        return ImageUtil.decompressImage(dbImage.get().getDataImage());

    }
}
