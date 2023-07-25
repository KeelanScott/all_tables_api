package org.kainos.ea.dao;
import org.kainos.ea.exception.DatabaseConnectionException;
import java.sql.*;

public class DatabaseConnector {
    private static Connection conn;

    public static Connection getConnection() throws SQLException, DatabaseConnectionException {
        String user, password, host, name;

        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        try {
            System.out.println("Connecting to database...");
            // get info form the env variables
            user = System.getenv("DB_USERNAME");
            password = System.getenv("DB_PASSWORD");
            host = System.getenv("DB_HOST");
            name = System.getenv("DB_NAME");

            if (user == null || password == null || host == null)
                throw new IllegalAccessException("Properties files must exist " + "and must contain user, password, name and host properties.");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);

            if(conn == null){
                throw new SQLException("Connection is null");
            }

            return conn;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DatabaseConnectionException();
        } finally {
            System.out.println("I will always run!");
        }
    }
}
