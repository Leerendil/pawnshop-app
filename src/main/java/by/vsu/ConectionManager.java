package by.vsu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5433/pawnshop";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2795";


    public static Connection open() {
        try {

            Class.forName("org.postgresql.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private ConectionManager() {}
}
