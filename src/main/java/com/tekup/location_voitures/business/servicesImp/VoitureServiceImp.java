package com.tekup.location_voitures.business.servicesImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.location_voitures.business.services.VoitureService;
import com.tekup.location_voitures.dao.entities.Voiture;
import com.tekup.location_voitures.dao.repositories.VoitureRepository;

@Service
public class VoitureServiceImp implements VoitureService {

    @Autowired
    VoitureRepository voitureRepository;

    @Override
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @Override
    public Optional<Voiture> getVoitureById(Long id) {
        return voitureRepository.findById(id);
    }

    @Override
    public Voiture addVoiture(Voiture v) {
        return voitureRepository.save(v);
    }

    @Override
    public Voiture updateVoiture(Voiture v) {
        return voitureRepository.save(v);

    }

    @Override
    public void deleteVoiture(Long id) {
        voitureRepository.deleteById(id);
    }

    @Override
    public List<Voiture> trouverVoituresParMarque(String marque) {
        return voitureRepository.findByMarque(marque);
    }

    @Override
    public List<Voiture> trouverVoituresParCouleur(String couleur) {
        return voitureRepository.findByCouleur(couleur);
    }

    @Override
    public List<Voiture> trouverVoituresParImatriculation(String imatriculation) {
        return voitureRepository.findByImatriculation(imatriculation);
    }

}