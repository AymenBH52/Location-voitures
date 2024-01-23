package com.tekup.location_voitures.Web.models.requests;

import com.tekup.location_voitures.dao.entities.Client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientForm {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String adresse;

    // Constructeur prenant un objet Client
    public ClientForm(Client client) {
        this.id = client.getId();
        this.nom = client.getNom();
        this.prenom = client.getPrenom();
        this.cin = client.getCin();
        this.telephone = client.getTelephone();
        this.adresse = client.getAdresse();
    }

}