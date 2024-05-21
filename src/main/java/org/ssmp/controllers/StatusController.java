package org.ssmp.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ssmp.model.Status;
import org.ssmp.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("/status")
@CrossOrigin
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/all")
    public List<Status> statusList(){
        return statusService.getStatusList();
    }
}
