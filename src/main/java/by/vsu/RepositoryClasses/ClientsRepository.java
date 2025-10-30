package by.vsu.RepositoryClasses;

import by.vsu.ConectionManager;
import by.vsu.tableClasses.Clients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ClientsRepository implements Repository<Clients> {

    @Override
    public void add(Clients client) {
        String sqlQuery = """
                INSERT INTO clients (passport_number, first_name, last_name, phone_number) VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = ConectionManager.open();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, client.getPassport_number());
            statement.setString(2, client.getFirst_name());
            statement.setString(3, client.getLast_name());
            statement.setInt(4, client.getPhone_number());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = """
                DELETE FROM clients WHERE client_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Clients client) {
        String sqlQuery = """
                UPDATE clients SET passport_number = ?, first_name = ?, last_name = ?, phone_number = ? WHERE client_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, client.getPassport_number());
            statement.setString(2, client.getFirst_name());
            statement.setString(3, client.getLast_name());
            statement.setInt(4, client.getPhone_number());

            statement.setInt(5, client.getClient_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Clients getById(int id) {
        String sqlQuery = """
                SELECT * FROM clients WHERE client_id = ?
                """;
        Clients client = null;

        try (Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                client = new Clients(
                        resultSet.getInt("client_id"),
                        resultSet.getInt("passport_number"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("phone_number")
                );
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return client;
    }

    @Override
    public List<Clients> getAll() {
        String sqlQuery = """
                SELECT * FROM clients
                """;
        List<Clients> clients = new ArrayList<>();

        try (Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                clients.add(new Clients(
                        resultSet.getInt("client_id"),
                        resultSet.getInt("passport_number"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("phone_number")
                ));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }
}
