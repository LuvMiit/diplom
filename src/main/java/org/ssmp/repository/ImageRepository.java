package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.CarSMP;
import org.ssmp.model.ImageType;
import org.ssmp.model.Images;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Images, Long> {
    Optional<Images> findByCarAndType(CarSMP car, ImageType type);
}
