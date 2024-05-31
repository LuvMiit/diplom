package org.ssmp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.ssmp.model.TypeDocument;
import org.ssmp.service.TypeDocumentService;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/docTypes")
public class TypeDocumentController {

    private final TypeDocumentService typeDocumentService;

    @GetMapping("/all")
    public List<TypeDocument> getAllTypes(){
        return typeDocumentService.getAllTypes();
    }
}
