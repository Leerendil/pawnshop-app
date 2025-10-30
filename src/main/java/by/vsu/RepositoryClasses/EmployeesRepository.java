package by.vsu.RepositoryClasses;

import by.vsu.ConectionManager;
import by.vsu.tableClasses.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EmployeesRepository implements Repository<Employees> {
    @Override
    public void add(Employees employee) {
        String sqlQuety = """
                INSERT INTO employees (first_name, last_name, role_of_employee) VALUES (?, ?, ?)
                """;

        try(Connection connection = ConectionManager.open();
            PreparedStatement statement = connection.prepareStatement(sqlQuety)) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getRole_of_employee());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employees> getAll() {
        String sqlQuery = """
                SELECT * FROM employees
                """;
        List<Employees> employees = new ArrayList<>();

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                employees.add(new Employees(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role_of_employee")
                ));
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employees;
    }

    @Override
    public Employees getById(int id) {
        String sqlQuery = """
                SELECT * FROM employees WHERE employee_id = ?
                """;
        Employees employee = null;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                employee = new Employees(
                        resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("role_of_employee")
                );
            }

            resultSet.close();
        } catch (SQLException e ) {
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public void update(Employees employee) {
        String sqlQuery = """
                UPDATE employees SET first_name = ?, last_name = ?, role_of_employee = ? WHERE employee_id = ?
                """;

        try(Connection connection = ConectionManager.open();
        PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, employee.getFirst_name());
            statement.setString(2, employee.getLast_name());
            statement.setString(3, employee.getRole_of_employee());

            statement.setInt(4, employee.getEmployee_id());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sqlQuery = """
                DELETE FROM employees WHERE employee_id = ?
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
