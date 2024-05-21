package org.ssmp.controllers;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotoController {

    @PostMapping("/save")
    public ResponseEntity<?> uploadFile(@RequestParam("Object") MultipartFile file) {

            System.out.println(file);
            // Здесь можно добавить логику для обработки фотографий
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
