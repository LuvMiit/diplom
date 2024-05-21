package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.model.CarSMP;
import org.ssmp.service.CarService;
import org.ssmp.service.ImageService;

import java.io.IOException;
import java.util.Arrays;

@RestController
@CrossOrigin
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;
    private final CarService carService;

    @PostMapping("/post/{carPlates}")
    public ResponseEntity<?> uploadImage(@RequestPart(value = "image") MultipartFile file, @PathVariable("carPlates") String carPlates) throws IOException {
        System.out.println(file);
        String uploadImage = imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/get")
    public ResponseEntity<?> downloadImage() {
        CarSMP carSMP = new CarSMP();
        System.out.println("Сработал контроллер");
        byte[] imageData = imageService.downloadImage(carSMP);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
