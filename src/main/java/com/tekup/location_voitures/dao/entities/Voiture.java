package com.tekup.location_voitures.dao.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imatriculation;
    private String marque;
    private String couleur;
    private String dateMiseEnCirculation;
    private double prixLocation;
    @Column(nullable = true, length = 64)
    private String image;
    @OneToMany(mappedBy = "voiture")
    private List<Location> locations;

    @Transient
    public String getImagePath() {
        if (image == null || id == null)
            return null;
        return "/voiture-images/" + id + "/" + image;
    }

}