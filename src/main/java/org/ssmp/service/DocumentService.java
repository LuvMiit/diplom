package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.ssmp.model.Document;
import org.ssmp.repository.DocumentRepository;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final TypeDocumentService typeDocumentService;

    public void saveDocumentByCarPlates(String carPlates, MultipartFile file, String typeName) throws IOException {
        Document document = new Document();
        document.setCarPlates(carPlates);
        document.setTypeDocument(typeDocumentService.getTypeByName(typeName));
        document.setDataType(file.getContentType());
        document.setDocumentData(file.getBytes());
        documentRepository.save(document);
    }
}
