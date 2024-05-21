package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Status;
import org.ssmp.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getStatusList(){
        return statusRepository.findAll();
    }

    public Status getByStatusName(String statusName){
        return statusRepository.findByStatusName(statusName);
    }
}
