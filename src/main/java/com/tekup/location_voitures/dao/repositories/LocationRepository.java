package com.tekup.location_voitures.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.location_voitures.dao.entities.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}