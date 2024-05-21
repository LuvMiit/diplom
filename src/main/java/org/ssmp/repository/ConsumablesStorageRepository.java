package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.ConsumablesStorage;

@Repository
public interface ConsumablesStorageRepository extends JpaRepository<ConsumablesStorage, Long> {
}
