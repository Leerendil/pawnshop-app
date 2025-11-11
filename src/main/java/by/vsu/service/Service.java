package by.vsu.service;

import by.vsu.ConectionManager;
import by.vsu.repository.Repository;
import by.vsu.tableClasses.Clients;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Service <T> {
    protected final Repository<T> repository;

    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    public List<T> getAll() {
        try (Connection connection = ConectionManager.open()) {

            connection.setAutoCommit(false);

            List<T> entities = new ArrayList<>(repository.getAll(connection));

            connection.commit();

            return entities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public T getById(int id) {
        try (Connection connection = ConectionManager.open()) {
            connection.setAutoCommit(false);

            T entity = repository.getById(id, connection);

            connection.commit();
            return entity;
        } catch (SQLException e) {
            throw new RuntimeException("Error getting entity by id: " + e.getMessage(), e);
        }
    }

    public void delete(int id) {
        try (Connection connection = ConectionManager.open()) {
            connection.setAutoCommit(false);

            repository.delete(id, connection);

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting entity: " + e.getMessage(), e);
        }
    }

    public void add(T entity) {
        try (Connection connection = ConectionManager.open()) {

            connection.setAutoCommit(false);

            repository.add(entity, connection);

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T entity) {
        try(Connection connection = ConectionManager.open()) {

            connection.setAutoCommit(false);

            repository.update(entity, connection);

            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
