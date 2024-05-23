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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    ) throws IOException, ParseException {
        CarSMP carSMP = carService.findCarByPlates(carPlates);

        ImageType typeImage = imageTypesService.getImageType(type);
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
            @PathVariable("date") String date) throws ParseException {

        System.out.println("первый");
        CarSMP carSMP = carService.findCarByPlates(carPlates);
        ImageType type = imageTypesService.getImageType(name);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse(date);
        System.out.println(startDate);
        byte[] imageData = imageService.downloadImage(carSMP, type, startDate);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
