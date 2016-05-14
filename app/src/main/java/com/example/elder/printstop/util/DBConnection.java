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
    //private static String address = "10.0.3.2";
    //HOME
    //private static String address = "10.0.0.104";
    //MONITORA
    //private static String address = "172.16.1.139";
    //UFSCAR
    //private static String address = "186.219.94.49";
    //Rodolfo
    private static String address = "192.168.1.131";
    private static String db = "mycloudprinter";
    private static String user = "root";
    private static String password = "";

    public static DBConnection getInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        if (connection == null) {
               Class.forName("com.mysql.jdbc.Driver").newInstance();
               connection = DriverManager.getConnection("jdbc:mysql://" + address + ":3306/" + db, user, password);
           }
        return ourInstance;
    }

//    private Connection getConnection( String db, String user, String password){
//
//        connection = DriverManager.getConnection("jdbc:mysql://" + address + ":3306/" + db, user, password);
//        if(connection == null)
//            connection = getConnection()
//    }

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
