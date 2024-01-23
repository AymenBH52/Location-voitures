package com.tekup.location_voitures.dao.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dateDebut;
    private String dateFin;
    private String TypeGarantie;
    private double MontantGarantie;
    private double FraisLocation;
    private String ModePaiement;

    @ManyToOne
    @JoinColumn(name = "voiture_id")
    private Voiture voiture;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}