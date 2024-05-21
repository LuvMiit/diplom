package org.ssmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.ssmp.model.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
}
