package com.tekup.location_voitures.Web.models.requests;

import java.util.Date;

import com.tekup.location_voitures.dao.entities.Client;
import com.tekup.location_voitures.dao.entities.Location;
import com.tekup.location_voitures.dao.entities.Voiture;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LocationForm {

    private Long id;

    private String dateDebut;
    private String dateFin;
    private String TypeGarantie;
    private double MontantGarantie;
    private double FraisLocation;
    private String ModePaiement;

    private Voiture voiture;
    private Client client;

    //Ajout
    public LocationForm(Location location) {
        this.id = location.getId();
        this.dateDebut = location.getDateDebut();
        this.dateFin = location.getDateFin();
        this.TypeGarantie = location.getTypeGarantie();
        this.MontantGarantie = location.getMontantGarantie();
        this.FraisLocation = location.getFraisLocation();
        this.ModePaiement = location.getModePaiement();
        this.voiture = location.getVoiture();
        this.client = location.getClient();
    }

}