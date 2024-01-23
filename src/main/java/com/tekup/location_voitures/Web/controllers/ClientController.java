package com.tekup.location_voitures.Web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tekup.location_voitures.Web.models.requests.ClientForm;
import com.tekup.location_voitures.business.services.ClientService;
import com.tekup.location_voitures.dao.entities.Client;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    // liste des clients :
    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clientList";
    }

    // Recherche d'un client par son id :

    @GetMapping("/{id}")
    public String getClientById(@PathVariable Long id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("client", client.get());
            return "clientDetails";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

    // Méthode d'affichage de la formulaire d'ajout d'un client :

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("clientForm", new ClientForm());
        return "addClient";
    }

    // Méthode d'ajout d'un client :

    @PostMapping("/add")
    public String addClient(@ModelAttribute("clientForm") ClientForm clientForm, Model model) {
        Client client = new Client(null, clientForm.getNom(), clientForm.getPrenom(), clientForm.getCin(),
                clientForm.getTelephone(), clientForm.getAdresse());
        try {
            clientService.addClient(client);
            return "redirect:/clients";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to create new customer");
            return "error";
        }
    }

    // Méthode d'affichage de la formulaire de modification d'un client :

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            model.addAttribute("clientForm", new ClientForm(client.get()));
            return "editClient";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

    // Méthode de modification d'un client :

    @PostMapping("/edit/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("clientForm") ClientForm clientForm,
            Model model) {
        Optional<Client> existingClient = clientService.getClientById(id);
        if (existingClient.isPresent()) {
            Client updatedClient = existingClient.get();
            updatedClient.setNom(clientForm.getNom());
            updatedClient.setPrenom(clientForm.getPrenom());
            updatedClient.setCin(clientForm.getCin());
            updatedClient.setTelephone(clientForm.getTelephone());
            updatedClient.setAdresse(clientForm.getAdresse());
            clientService.updateClient(updatedClient);
            return "redirect:/clients";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

    // Méthode de suppression d'un client :

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            clientService.deleteClient(id);
            return "redirect:/clients";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

    // page d'accueil :

    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
