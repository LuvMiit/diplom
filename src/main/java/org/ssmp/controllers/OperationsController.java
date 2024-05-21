package org.ssmp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Operations;
import org.ssmp.service.OperationsService;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService operationsService;

    public OperationsController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @GetMapping("/all")
    public List<Operations> operationsList(){
        return operationsService.getOperationsList();
    }
}
