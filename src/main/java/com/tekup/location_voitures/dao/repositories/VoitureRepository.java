package com.tekup.location_voitures.dao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tekup.location_voitures.dao.entities.Voiture;

@Repository
public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    List<Voiture> findByMarque(String marque);

    List<Voiture> findByCouleur(String couleur);

    List<Voiture> findByImatriculation(String imatriculation);

}