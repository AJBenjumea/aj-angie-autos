package com.automobile.autodealer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutosRepository extends JpaRepository<Auto, Long> {
    Auto findByVinContains(String vin);
}
