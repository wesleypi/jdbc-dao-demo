package model;

import model.exceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class FactoryConnection {

    private FactoryConnection(){}

    public static Connection getConnection() throws SQLException, DBException{
        Properties properties = loadProperties();
        return DriverManager.getConnection(properties.getProperty("url"), properties);
    }

    public static void closeConnection(Connection connection){
        try {
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection, Statement stmt){
        closeConnection(connection);
        try {
            if (stmt != null){
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection connection, Statement stmt, ResultSet rtst){
        closeConnection(connection, stmt);
        try {
            if (rtst != null){
                rtst.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties() throws DBException {
        try (FileInputStream fs = new FileInputStream("src/db.properties")){
            Properties properties = new Properties();
            properties.load(fs);
            return properties;
        } catch (IOException e) {
            throw new DBException("something going wrong at loading properties: \n"+e);
        }
    }
}
