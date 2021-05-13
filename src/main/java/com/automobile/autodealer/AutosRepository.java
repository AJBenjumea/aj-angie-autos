package com.automobile.autodealer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Long> {
    Optional<Auto> findByVinContains(String vin);
}
