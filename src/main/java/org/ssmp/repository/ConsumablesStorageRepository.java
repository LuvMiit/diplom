package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Consumables;
import org.ssmp.model.ConsumablesStorage;
import org.ssmp.model.Storage;

import java.util.List;

@Repository
public interface ConsumablesStorageRepository extends JpaRepository<ConsumablesStorage, Long> {
    List<ConsumablesStorage> findAllByStorage(Storage storage);

    ConsumablesStorage findByStorageAndConsumable(Storage storage, Consumables consumables);
}
