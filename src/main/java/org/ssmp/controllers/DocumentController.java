package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ssmp.dtos.documents.InfoDocumentAcceptDTO;
import org.ssmp.dtos.documents.InfoForSaveDocDTO;
import org.ssmp.dtos.documents.InfoForWriteDownDocDTO;
import org.ssmp.model.Document;
import org.ssmp.service.DocumentService;

import java.io.IOException;
import java.util.Arrays;

@RestController
@CrossOrigin
@RequestMapping("/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/bcast")
    public ResponseEntity<?> saveDocumentBcast(
            @RequestBody InfoForSaveDocDTO infoForSaveDocDTO
            ) throws IOException {
        System.out.println("Пришло в контроллер "+ infoForSaveDocDTO);
        try{
            documentService.saveDocumentBcast(infoForSaveDocDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
    @PostMapping("/writedown")
    public ResponseEntity<?> saveDocumentWriteDown(
            @RequestBody InfoForWriteDownDocDTO infoForWriteDownDocDTO
    ) throws IOException {
        System.out.println("Пришло в контроллер "+ infoForWriteDownDocDTO);
        try{
            documentService.saveDocumentWriteDown(infoForWriteDownDocDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<?> saveDocumentAccept(
            @RequestBody InfoDocumentAcceptDTO infoDocumentAcceptDTO
    ) throws IOException {

        try{
            System.out.println("Пришло в контроллер "+ infoDocumentAcceptDTO);
            documentService.saveDocumentAccept(infoDocumentAcceptDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @GetMapping("/get/{carPlates}/{type}")
    public ResponseEntity<?> getDocumentByPlateAndType(
            @PathVariable("carPlates") String carPlates,
            @PathVariable("type") String type
    ) throws IOException {
        Document document = documentService.getDocByPlateAndType(carPlates,type);
        if(document != null){
            byte[] myBytes = document.getDocumentData();

            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                    .body(myBytes);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
