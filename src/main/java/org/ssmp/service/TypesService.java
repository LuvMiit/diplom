package org.ssmp.service;

import org.springframework.stereotype.Service;
import org.ssmp.model.Types;
import org.ssmp.repository.TypesRepository;

import java.util.List;

@Service
public class TypesService {

    private final TypesRepository typesRepository;

    public TypesService(TypesRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public List<Types> getTypesList(){
        return typesRepository.findAll();
    }

    public Types getByTypeName(String typeName){
        return typesRepository.findByTypeName(typeName);
    }
}
