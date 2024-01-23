package com.tekup.location_voitures.Web.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.tekup.location_voitures.Web.models.requests.VoitureForm;
import com.tekup.location_voitures.business.services.VoitureService;
import com.tekup.location_voitures.dao.entities.Voiture;

@RequestMapping("/voitures")
@Controller
public class VoitureController {

    @Autowired
    VoitureService voitureService;

    @GetMapping()
    public String getAllVoitures(Model model) {
        model.addAttribute("voitures", voitureService.getAllVoitures());
        return "car";
    }

    @PostMapping("/add")
    public String createVoiture(@ModelAttribute("voitureForm") Voiture voiture,
            @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        voiture.setImage(fileName);
        Voiture savedVoiture = voitureService.addVoiture(voiture);
        String uploadDir = "./voiture-images/" + savedVoiture.getId();
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("could not save " + fileName);
        }

        return "redirect:/list";

    }

    // Méthode d'affichage de la formulaire de modification d'une voiture:

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

    // Méthode de modification d'une voiture :

    @PostMapping("/edit/{id}")
    public String updateVoiture(@PathVariable Long id, @ModelAttribute("voitureForm") VoitureForm voitureForm,
            Model model) {
        Optional<Voiture> existingVoiture = voitureService.getVoitureById(id);
        if (existingVoiture.isPresent()) {
            Voiture updatedVoiture = existingVoiture.get();
            updatedVoiture.setMarque(voitureForm.getMarque());
            updatedVoiture.setPrixLocation(voitureForm.getPrixLocation());
            updatedVoiture.setImatriculation(voitureForm.getImatriculation());
            updatedVoiture.setDateMiseEnCirculation(voitureForm.getDateMiseEnCirculation());
            updatedVoiture.setImage(voitureForm.getImage());
            voitureService.updateVoiture(updatedVoiture);
            return "redirect:/list";
        } else {
            model.addAttribute("error", "Car not found");
            return "error";
        }
    }

    @GetMapping("/rechercheCouleur")
    public String trouverVoituresParCouleur(@RequestParam String couleur, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParCouleur(couleur);
        model.addAttribute("voitures", voitures);
        return "car";
    }

    @GetMapping("/rechercheImmatriculation")
    public String trouverVoituresParImatriculation(@RequestParam String imatriculation, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParImatriculation(imatriculation);
        model.addAttribute("voitures", voitures);
        return "list";
    }

    @GetMapping("/recherche")
    public String trouverVoituresParMarqueEtCouleur(@RequestParam String marque, Model model) {
        List<Voiture> voitures = voitureService.trouverVoituresParMarque(marque);
        model.addAttribute("voitures", voitures);
        return "car";
    }

    @GetMapping("/delete/{id}")
    public String deleteVoiture(@PathVariable Long id, Model model) {
        Optional<Voiture> voiture = voitureService.getVoitureById(id);
        if (voiture.isPresent()) {

            voitureService.deleteVoiture(id);
            return "redirect:/list";
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

}