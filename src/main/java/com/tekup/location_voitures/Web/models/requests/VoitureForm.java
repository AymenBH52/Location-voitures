package com.tekup.location_voitures.Web.models.requests;

import com.tekup.location_voitures.dao.entities.Client;
import com.tekup.location_voitures.dao.entities.Voiture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VoitureForm {
    private Long id;
    private String imatriculation;
    private String marque;
    private String dateMiseEnCirculation;
    private double prixLocation;
    private String image;
    private String couleur;

    // Constructeur prenant un objet Voiture
    public VoitureForm(Voiture voiture) {
        this.id = voiture.getId();
        this.imatriculation = voiture.getImatriculation();
        this.dateMiseEnCirculation = voiture.getDateMiseEnCirculation();
        this.marque = voiture.getMarque();
        this.prixLocation = voiture.getPrixLocation();
        this.image = voiture.getImage();
        this.couleur = voiture.getCouleur();
    }
}