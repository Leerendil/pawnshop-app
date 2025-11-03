package by.vsu.RepositoryClasses;

import by.vsu.ConectionManager;
import by.vsu.tableClasses.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements Repository<Transactions>{

    @Override
    public void add(Transactions transaction) {
        String sqlQuery = """
                INSERT INTO transactions(transaction_type, amount, transaction_date, fk_employee_id, fk_item_id) VALUES (?, ?, ?, ?, ?)
                """;

        try(Connection connection = ConectionManager.open();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, transaction.getTransactionType());
            statement.setDouble(2, transaction.getAmount());
            statement.setInt(3, transaction.getTransactionDate());
            statement.setInt(4, transaction.getFkEmployeeId());
            statement.setInt(5, transaction.getFkItemId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Transactions> getAll() {
        String sqlQuery = """
                SELECT * FROM transactions
                """;
        List<Transactions> transactions = new ArrayList<>();

        try (Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                transactions.add(new Transactions(
                        resultSet.getInt("transaction_id"),
                        resultSet.getString("transaction_type"),
                        resultSet.getDouble("amount"),
                        resultSet.getInt("transaction_date"),
                        resultSet.getInt("fk_employee_id"),
                        resultSet.getInt("fk_item_id")
                ));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }

    @Override
    public Transactions getById(int id) {
        String sqlQuery = """
                SELECT * FROM transactions WHERE transaction_id = ?
                """;
        Transactions transaction = null;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                transaction = new Transactions(
                        resultSet.getInt("transaction_id"),
                        resultSet.getString("transaction_type"),
                        resultSet.getDouble("amount"),
                        resultSet.getInt("transaction_date"),
                        resultSet.getInt("fk_employee_id"),
                        resultSet.getInt("fk_item_id")
                );
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transaction;
    }

    @Override
    public void update(Transactions transaction) {
        String sqlQuery = """
                UPDATE transactions SET transaction_type = ?, transaction_date = ?, amount = ?, fk_employee_id = ?, fk_item_id = ? WHERE transaction_id = ?
                """;

        try (Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, transaction.getTransactionType());
            statement.setInt(2, transaction.getTransactionDate());
            statement.setDouble(3, transaction.getAmount());
            statement.setInt(4, transaction.getFkEmployeeId());
            statement.setInt(5, transaction.getFkItemId());

            statement.setInt(6, transaction.getTransactionId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = """
                DELETE FROM transactions WHERE transaction_id = ?
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
