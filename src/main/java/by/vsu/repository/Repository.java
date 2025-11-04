package by.vsu.repository;

import java.sql.Connection;
import java.util.List;

public interface Repository<T> {
    void add(T item, Connection connection);
    List<T> getAll(Connection connection);
    T getById(int id, Connection connection);
    void update(T item, Connection connection);
    void delete(int id, Connection connection);

}

