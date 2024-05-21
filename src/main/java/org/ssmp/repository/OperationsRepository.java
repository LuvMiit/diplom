package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Operations;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, Long> {
}
