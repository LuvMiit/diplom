package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Consumables;

@Repository
public interface ConsumablesRepository extends JpaRepository<Consumables, Long> {
}
