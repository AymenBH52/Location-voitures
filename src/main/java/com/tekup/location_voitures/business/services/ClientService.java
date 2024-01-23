package com.tekup.location_voitures.business.services;

import java.util.List;
import java.util.Optional;

import com.tekup.location_voitures.dao.entities.Client;


public interface ClientService {

    public List <Client> getAllClients();

    public Optional<Client> getClientById(Long id);

    public Client addClient (Client c); 

    public Client updateClient (Client c ); 

    public void deleteClient(Long id);
    
}