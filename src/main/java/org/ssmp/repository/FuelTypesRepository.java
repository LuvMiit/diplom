package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.FuelTypes;

@Repository
public interface FuelTypesRepository extends JpaRepository<FuelTypes, Integer> {
    FuelTypes findByFuelName(String fuelName);
}
