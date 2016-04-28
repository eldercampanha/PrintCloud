package com.example.elder.printstop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Elder on 4/24/2016.
 */
public class DBConnection {
    private static DBConnection ourInstance = new DBConnection();
    private static Connection connection;
    //ANDROID EMULATOR
    //private static String address = "10.0.2.2";
    //GENYMOTION
    private static String address = "10.0.3.2";
    private static String db = "mycloudprinterteste2";
    private static String user = "admin";
    private static String password = "";

    public static DBConnection getInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        if (connection == null) {
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               connection = DriverManager.getConnection("jdbc:mysql://" + address + ":3306/" + db, user, password);
           }
        return ourInstance;
    }

    public synchronized Connection getConnection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        return connection;
    }

    public synchronized void closeConnection() throws SQLException {
        if(connection != null && !connection.isClosed())
            connection.close();
        connection = null;
    }

    public synchronized ResultSet executeQuery(String sql) throws SQLException {
        ResultSet rs = null;
        if (connection != null) {
            rs= connection.createStatement().executeQuery(sql);
        }
        return rs;
    }

    public synchronized void executeUpdate(String sql) throws SQLException {
        if (connection != null) {
            connection.createStatement().executeUpdate(sql);
        }
    }

    private DBConnection() {
    }
}
