package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ssmp.model.TypeDocument;
import org.ssmp.repository.TypeDocumentRepository;

@Service
@RequiredArgsConstructor
public class TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    public TypeDocument getTypeByName(String typeName){
        return typeDocumentRepository.findByTypeName(typeName);
    }
}
