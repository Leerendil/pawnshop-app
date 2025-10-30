package by.vsu.RepositoryClasses;

import by.vsu.ConectionManager;
import by.vsu.tableClasses.Categories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class CategoriesRepository implements Repository<Categories> {

    @Override
    public void add(Categories category) {
        String sqlQuery = """
                INSERT INTO categories (category_name) VALUES (?)
                """;

        try(Connection connection = ConectionManager.open();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, category.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Categories> getAll() {
        String sqlQuery = """
                SELECT * FROM categories
                """;
        List<Categories> categories = new ArrayList<>();

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                categories.add(new Categories(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name"))
                );
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Categories getById(int id) {
        String sqlQuery = """
                SELECT * FROM categories WHERE category_id = ?
                """;
        Categories category = null;

        try(Connection connection = ConectionManager.open();
            PreparedStatement statement = connection.prepareStatement(sqlQuery);) {
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                category = new Categories(resultSet.getString("categorie_name"));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return category;
    }

    @Override
    public void update(Categories category) {
        String sqlQuery = """
                UPDATE categories SET category_name = ? WHERE category_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, category.getName());

            statement.setInt(2, category.getCategory_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = """
                DELETE FROM categories WHERE category_id = ?
                """;

        try (Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
