package by.vsu.RepositoryClasses;

import by.vsu.ConectionManager;
import by.vsu.tableClasses.Items;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemsRepository implements Repository<Items> {

    @Override
    public void add(Items item) {
        String sqlQuery = """
                INSERT INTO items (item_name, item_pledge_amount, item_status, fk_client_id, fk_category_id) VALUES (?, ?, ?, ?, ?) 
                """;

        try(Connection connection = ConectionManager.open();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, item.getItem_name());
            statement.setDouble(2, item.getItem_pledge_amount());
            statement.setString(3, item.getItem_status());
            statement.setInt(4, item.getFk_client_id());
            statement.setInt(5, item.getFk_category_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Items> getAll() {
        String sqlQuery = """
                SELECT * FROM items
                """;
        List<Items> items = new ArrayList<>();

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                items.add(new Items(
                        resultSet.getInt("item_id"),
                        resultSet.getString("item_name"),
                        resultSet.getDouble("item_pledge_amount"),
                        resultSet.getString("item_status"),
                        resultSet.getInt("fk_client_id"),
                        resultSet.getInt("fk_category_id")
                ));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    @Override
    public Items getById(int id) {
        String sqlQuery = """
                SELECT * FROM items WHERE item_id = ?
                """;
        Items item = null;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                item = new Items(
                        resultSet.getInt("item_id"),
                        resultSet.getString("item_name"),
                        resultSet.getDouble("item_pledge_amount"),
                        resultSet.getString("item_status"),
                        resultSet.getInt("fk_client_id"),
                        resultSet.getInt("fk_category_id")
                );
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return item;
    }

    @Override
    public void update(Items item) {
        String sqlQuery = """
                UPDATE
                    items
                SET
                    item_name = ?, item_pledge_amount = ?, item_status = ?, fk_client_id = ?, fk_category_id = ?
                WHERE
                    item_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, item.getItem_name());
            statement.setDouble(2, item.getItem_pledge_amount());
            statement.setString(3, item.getItem_status());
            statement.setInt(4, item.getFk_client_id());
            statement.setInt(5, item.getFk_category_id());

            statement.setInt(6, item.getItem_id());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = """
                DELETE FROM items WHERE item_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
