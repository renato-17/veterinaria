package com.example.centromedicoupc.repository;

import com.example.centromedicoupc.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    private final List<Client> clients = new ArrayList<>() {
        {
            add(Client.builder().id(1L).name("Carlos").email("carlos@gmail.com").phone("987442267").build());
            add(Client.builder().id(2L).name("Carla").email("carla@gmail.com").phone("983442267").build());
            add(Client.builder().id(3L).name("Marcos").email("marcos@gmail.com").phone("987442267").build());
            add(Client.builder().id(4L).name("Maria").email("maria@gmail.com").phone("987446267").build());
        }
    };

    public Client getById(Long id){
        for (Client client : clients) {
            if (client.getId().equals(id)) {
                return client;
            }
        }
        return null;
    }

    public List<Client> getAll(){return clients;}
}
