package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.model.CarSMP;
import org.ssmp.model.ImageType;
import org.ssmp.model.Images;
import org.ssmp.service.CarService;
import org.ssmp.service.ImageService;
import org.ssmp.service.ImageTypesService;

import javax.imageio.ImageTypeSpecifier;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final CarService carService;
    private final ImageTypesService imageTypesService;

    @PostMapping("/post/{carPlates}")
    public ResponseEntity<?> uploadImage(
            @RequestPart(value = "image") MultipartFile file,
            @PathVariable("carPlates") String carPlates,
            @RequestPart(value = "type") String type
    ) throws IOException {
        System.out.println(carPlates);
        CarSMP carSMP = carService.findCarByPlates(carPlates);
        System.out.println(carSMP);

        ImageType typeImage = imageTypesService.getImageType(type);
        System.out.println(typeImage);
        String uploadImage = imageService.uploadImage(
                file,
                carSMP,
                typeImage
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);

    }

    @GetMapping("/get/{carPlates}/{type}/{date}")
    public ResponseEntity<?> downloadImage(
            @PathVariable("carPlates") String carPlates,
            @PathVariable("type") String name,
            @PathVariable("date") String date) {

        System.out.println("первый");
        CarSMP carSMP = carService.findCarByPlates(carPlates);
        ImageType type = imageTypesService.getImageType(name);
        byte[] imageData = imageService.downloadImage(carSMP, type);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
