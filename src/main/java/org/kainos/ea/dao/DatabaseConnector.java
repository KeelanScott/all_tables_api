package org.kainos.ea.dao;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;


public class DatabaseConnector {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        String user, password, host, name;

        if (conn != null && !conn.isClosed()) {
            return conn;
        }

        try(FileInputStream propStream = new FileInputStream("db.properties")) {

            Properties props = new Properties();
            props.load(propStream);

            user = props.getProperty("user");
            password = props.getProperty("password");
            host = props.getProperty("host");
            name = props.getProperty("name");

            if (user == null || password == null || host == null)
                throw new IllegalAccessException("Properties files must exist " + "and must contain user, password, name and host properties.");

            conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + name + "?useSSL=false", user, password);
            return conn;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("I will always run!");
        }

        if(conn == null){
            throw new SQLException("Connection is null");
        }
        return conn;
    }

}
