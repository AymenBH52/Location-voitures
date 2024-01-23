package com.tekup.location_voitures.business.services;

import java.util.List;
import java.util.Optional;

import com.tekup.location_voitures.Web.models.requests.LocationForm;
import com.tekup.location_voitures.dao.entities.Location;

public interface LocationService {

    public List<LocationForm> getAllLocations();

    public Location addLocation(LocationForm locationForm);


    public void deleteLocation(Long id, Long idVoiture, Long idClient);

    public Location updateLocation(Location l);

    public Optional<Location> getLocationById(Long id);
    

}