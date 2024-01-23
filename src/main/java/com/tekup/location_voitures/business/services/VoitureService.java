package com.tekup.location_voitures.business.services;

import java.util.List;
import java.util.Optional;

import com.tekup.location_voitures.dao.entities.Voiture;

public interface VoitureService {

    public List<Voiture> getAllVoitures();

    public Optional<Voiture> getVoitureById(Long id);

    public Voiture addVoiture(Voiture v);

    public Voiture updateVoiture(Voiture v);

    public List<Voiture> trouverVoituresParMarque(String marque);

    public List<Voiture> trouverVoituresParCouleur(String couleur);

    public List<Voiture> trouverVoituresParImatriculation(String imatriculation);

    public void deleteVoiture(Long id);
}