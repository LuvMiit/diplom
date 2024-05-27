package org.ssmp.controllers;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.service.DocumentService;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/add/{carPlates}/{typeDocument}")
    public void saveDocumentByCarPlates(
            @PathVariable("carPlates") String carPlates,
            @PathVariable("typeDocument") String typeDocument,
            @RequestPart("file")MultipartFile file
    ) throws IOException {
        try {
            documentService.saveDocumentByCarPlates(carPlates, file, typeDocument);
        }catch (Exception e){
            System.out.println("ОШИБКА ПРИ СОХРАНЕНИИ  "+e );
        }
    }
}
