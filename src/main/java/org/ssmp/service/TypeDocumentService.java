package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.TypeDocument;
import org.ssmp.repository.TypeDocumentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocument getTypeByName(String typeName){
        return typeDocumentRepository.findByTypeName(typeName);
    }

    public List<TypeDocument> getAllTypes() {
        return typeDocumentRepository.findAll();
    }
}
