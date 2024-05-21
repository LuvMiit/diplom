package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Station;

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {
    Station findByStationName(String stationName);
}
