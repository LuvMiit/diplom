package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.TypeDocument;

@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    TypeDocument findByTypeName(String typeName);
}
