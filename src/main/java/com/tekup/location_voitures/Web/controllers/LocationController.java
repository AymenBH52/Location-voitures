package com.tekup.location_voitures.Web.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tekup.location_voitures.Web.models.requests.LocationForm;
import com.tekup.location_voitures.business.services.LocationService;
import com.tekup.location_voitures.dao.entities.Client;
import com.tekup.location_voitures.dao.entities.Location;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public String getAllLocations(Model model) {
        List<LocationForm> ListlocationForm = locationService.getAllLocations();
        model.addAttribute("locationForm", ListlocationForm);

        return "ListLocation";
    }

    @PostMapping("/addLocation")
    public String addLocation(@ModelAttribute("locationForm") LocationForm locationForm, Model model) {

        try {
            locationService.addLocation(locationForm);

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to create new location");
            return "error";
        }
        return "redirect:/location";
    }


    // Modification d'une location :

    @PostMapping("/edit/{id}")
    public String updateLocation(@PathVariable Long id, @ModelAttribute("locationForm") LocationForm locationForm ,Model model) {
            
        Optional<Location> existingLocation = locationService.getLocationById(id);
        if (existingLocation.isPresent()) {
            Location updatedLocation = existingLocation.get();
            updatedLocation.setDateDebut(locationForm.getDateDebut());
            updatedLocation.setDateFin(locationForm.getDateFin());
            updatedLocation.setFraisLocation(locationForm.getFraisLocation());
            updatedLocation.setModePaiement(locationForm.getModePaiement());
            updatedLocation.setMontantGarantie(locationForm.getMontantGarantie());
            updatedLocation.setTypeGarantie(locationForm.getTypeGarantie());
            updatedLocation.setVoiture(locationForm.getVoiture()); // Add voiture
            updatedLocation.setClient(locationForm.getClient()); // Add client
            locationService.updateLocation(updatedLocation);
            locationService.updateLocation(updatedLocation);
locationService.updateLocation(updatedLocation);
            return "redirect:/location";
        } else {
            model.addAttribute("error", "Location not found");
            return "error";
        }
    }

    //Delete d'une location
    @GetMapping("/delete/{id}")
public String deleteLocation(@PathVariable(required = false) Long id,
                             @RequestParam(name = "idVoiture", required = false, defaultValue = "0") Long idVoiture,
                             @RequestParam(name = "idClient", required = false, defaultValue = "0") Long idClient,
                             Model model) {
    if (id == null || idVoiture == null || idClient == null) {
        model.addAttribute("error", "Invalid input parameters.");
        return "errorPage";
    }
    try {
        locationService.deleteLocation(id, idVoiture, idClient);
        return "redirect:/location";
    } catch (IllegalArgumentException | NoSuchElementException e) {
        model.addAttribute("error", "Invalid input parameters.");
        return "errorPage";
    }
}



}
