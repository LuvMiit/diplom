package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.CarSMP;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarSMP, Long> {
    CarSMP findByCarPlates(String carPlates);
}