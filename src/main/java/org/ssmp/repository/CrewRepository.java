package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Crew;
import org.ssmp.model.Staff;

@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {
    Crew findByDriver(Staff driver);
}
