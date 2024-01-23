package com.tekup.location_voitures.Web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tekup.location_voitures.Web.models.requests.ClientForm;
import com.tekup.location_voitures.Web.models.requests.LocationForm;
import com.tekup.location_voitures.Web.models.requests.UserForm;
import com.tekup.location_voitures.Web.models.requests.VoitureForm;
import com.tekup.location_voitures.business.services.ClientService;
import com.tekup.location_voitures.business.services.LocationService;
import com.tekup.location_voitures.business.services.VoitureService;
import com.tekup.location_voitures.dao.entities.AuthenticationRequest;
import com.tekup.location_voitures.dao.entities.Client;
import com.tekup.location_voitures.dao.entities.Location;
import com.tekup.location_voitures.dao.entities.Voiture;

@Controller
public class AdminController {

    @Autowired
    ClientService clientService;

    @Autowired
    VoitureService voitureService;

    @Autowired
    LocationService locationService;

    // Affichage de Dashbord Admin:
    @GetMapping("/Admin")
    public String getDashboard() {
        return "admin";
    }

    // Aaffichage de formulaire d'ajout d'un client dans le Dashbord Admin:
    @GetMapping("/addnewClient")
    public String showAddClientForm(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        return "addClient";
    }

    // Affichage de liste des clients dans le Dashbord Admin:
    @GetMapping("/listClients")
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clientList";
    }

    //Affichage de formulaire d'ajout d'une voiture dans le Dashbord Admin:
    
    @GetMapping("/addnewVoiture")
    public String showAddVoitureForm(Model model) {
        model.addAttribute("voitureForm", new VoitureForm());
        return "addVoiture";
    }

    //Affichage de Liste des voitures dans le Dhashbord Admin:
    @GetMapping("/list")
    public String getVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "list";
    }

    // Affichage de formulaire d'ajout d'une location dans le Dashbord Admin:
    @GetMapping("/addnewLocation")
    public String addLocation(Model model) {
        List<Client> clients = clientService.getAllClients();
        List<Voiture> voitures = voitureService.getAllVoitures();
        model.addAttribute("clients", clients);
        model.addAttribute("voitures", voitures);
        model.addAttribute("locationForm", new LocationForm());
        return "addLocation";
    }

    //Affichage de Liste des locations dans le Dhashbord Admin:
    @GetMapping("/location")
    public String getAllLocations(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "ListLocation";
    }

    //Affichage de la formulaire d'enregistrement
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    // Formulaire d'authentification :
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("authenticationRequest", new AuthenticationRequest());
        return "login";

    }

    //Recherche par couleur dans page home
    @GetMapping("/rechercheCouleur")
    public String trouverVoituresParEtCouleur(@RequestParam String couleur, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParCouleur(couleur);
        model.addAttribute("voitures", voitures);
        return "car";
    }

    //Recherche par couleur dans D Admin
    @GetMapping("/rechercheImatriculation")
    public String trouverVoituresParImatriculation(@RequestParam String imatriculation, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParImatriculation(imatriculation);
        model.addAttribute("voitures", voitures);
        return "list";
    }

    //Recherche par couleur dans P Home
    @GetMapping("/recherche")
    public String trouverVoituresParMarque(@RequestParam String marque, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParMarque(marque);
        model.addAttribute("voitures", voitures);
        return "car"; // Assurez-vous de retourner le nom correct de votre vue Thymeleaf
    }

    //affichage formulaire edit voiture
    @GetMapping("/edit/{id}")
    public String showEditVoitureForm(@PathVariable Long id, Model model) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {
            model.addAttribute("voitureForm", new VoitureForm(voiture.get()));
            return "update-voiture";
        } else {
            model.addAttribute("error", "Car not found");
            return "error";
        }
    }

    //Consultez la liste des voitures dans la page d'accueil
    @GetMapping("/HomeCars")
    public String showAllVoituresInHome(Model model) {
        model.addAttribute("voituresHome", voitureService.getAllVoitures());
        return "car";
    }

    //affichage formulaire edit location
    @GetMapping("/editLocation/{id}")
    public String showEditLocationForm(@PathVariable Long id, Model model) {
        Optional<Location> location = locationService.getLocationById(id);
        if (location.isPresent()) {
            model.addAttribute("locationForm", new LocationForm(location.get()));
            return "editLocation";
        } else {
            model.addAttribute("error", "location not found");
            return "error";
        }
    }

}
