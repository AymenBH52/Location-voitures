package com.tekup.location_voitures.business.servicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tekup.location_voitures.Web.models.requests.LocationForm;
import com.tekup.location_voitures.business.services.LocationService;
import com.tekup.location_voitures.dao.entities.Client;
import com.tekup.location_voitures.dao.entities.Location;
import com.tekup.location_voitures.dao.entities.Voiture;
import com.tekup.location_voitures.dao.repositories.LocationRepository;

@Service
public class LocationServiceImp implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    VoitureServiceImp voitureServiceImp;

    @Autowired
    ClientServiceImp clientServiceImp;


    //Liste locations
    @Override
    public List<LocationForm> getAllLocations() {
        List<LocationForm> ListlocationForm = new ArrayList<>();
        List<Location> locations = locationRepository.findAll();
        for (Location location : locations) {
            LocationForm locationForm = new LocationForm();
            locationForm.setDateDebut(location.getDateDebut());
            locationForm.setDateFin(location.getDateFin());
            locationForm.setFraisLocation(location.getFraisLocation());
            locationForm.setModePaiement(location.getModePaiement());
            locationForm.setMontantGarantie(location.getMontantGarantie());
            locationForm.setTypeGarantie(location.getTypeGarantie());
            locationForm.setVoiture(location.getVoiture()); // Get voiture
            locationForm.setClient(location.getClient()); // Get client
            ListlocationForm.add(locationForm);
        }
        return ListlocationForm;
    }


    //Ajout location
    @Override
    public Location addLocation(LocationForm locationForm) {
        Location location = new Location();
        location.setDateDebut(locationForm.getDateDebut());
        location.setDateFin(locationForm.getDateFin());
        location.setFraisLocation(locationForm.getFraisLocation());
        location.setModePaiement(locationForm.getModePaiement());
        location.setMontantGarantie(locationForm.getMontantGarantie());
        location.setTypeGarantie(locationForm.getTypeGarantie());
        location.setVoiture(locationForm.getVoiture()); // Add voiture
        location.setClient(locationForm.getClient()); // Add client
        return locationRepository.save(location);
    }


    //Delete location
    @Override
    public void deleteLocation(Long id, Long idVoiture, Long idClient) {

        Optional<Location> optionalLocation = locationRepository.findById(id);

        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            Voiture voiture = location.getVoiture();
            Client client = location.getClient();

            
            if (voiture.getId().equals(idVoiture) && client.getId().equals(idClient)) {
                
                locationRepository.delete(location);
            } else {
                throw new IllegalArgumentException("Invalid voiture or client ID.");
            }
        } else {
            throw new NoSuchElementException("La location avec l'ID " + id + " n'existe pas.");
        }
    }

    
    //Update location
    @Override
    public Location updateLocation(Location l) {
        return locationRepository.save(l);
    }

    //Trouver une location par son id
    @Override
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }




}