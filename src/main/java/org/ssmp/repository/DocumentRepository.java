package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Document findByCarPlates(String carPlates);

}
