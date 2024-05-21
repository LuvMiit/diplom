package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.ImageType;

@Repository
public interface ImageTypesRepository extends JpaRepository<ImageType, Long> {
    ImageType findByTypeName(String name);
}
