package by.vsu.service;

import by.vsu.repository.ClientsRepository;
import by.vsu.tableClasses.Clients;


public class ClientService extends Service<Clients> {

    public ClientService(ClientsRepository clientsRepository) {
        super(clientsRepository);
    }

}
