package by.vsu.service;

import by.vsu.ConectionManager;
import by.vsu.repository.ClientsRepository;
import by.vsu.tableClasses.Clients;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final ClientsRepository clientsRepository;

    public ClientService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Clients> getAll() {
        try (Connection connection = ConectionManager.open()) {
            connection.setAutoCommit(false);

            List<Clients> clients = new ArrayList<>(clientsRepository.getAll(connection));

            connection.commit();
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
