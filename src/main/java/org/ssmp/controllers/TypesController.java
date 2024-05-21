package org.ssmp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Types;
import org.ssmp.service.TypesService;

import java.util.List;

@RestController
@RequestMapping("/types")
@CrossOrigin
public class TypesController {

    private final TypesService typesService;

    public TypesController(TypesService typesService) {
        this.typesService = typesService;
    }

    @GetMapping("/all")
    public List<Types> typesList(){
        return typesService.getTypesList();
    }
}
