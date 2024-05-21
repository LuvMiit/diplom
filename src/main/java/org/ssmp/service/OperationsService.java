package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Operations;
import org.ssmp.repository.OperationsRepository;

import java.util.List;

@Service
public class OperationsService {

    private final OperationsRepository operationsRepository;

    public OperationsService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public List<Operations> getOperationsList(){
        return operationsRepository.findAll();
    }
}
