package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Types;

@Repository
public interface TypesRepository extends JpaRepository<Types, Long> {
    Types findByTypeName(String typeName);
}
