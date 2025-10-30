package by.vsu.RepositoryClasses;

import java.util.List;

public interface Repository<T> {
    void add(T item);
    <T> List<T> getAll();
    <T> T getById(int id);
    void update(T item);
    void delete(int id);

}

