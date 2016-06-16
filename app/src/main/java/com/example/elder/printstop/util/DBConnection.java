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

    //Rodolfo - Servidor Local
    private static String address = "177.180.161.140";
    private static String db = "mycloudprinter";
    private static String user = "admin";
    private static String password = "admin";

    //Servidor Online
//    private static String address = "mycloudprinter.mysql.dbaas.com.br";
//    private static String db = "mycloudprinter";
//    private static String user = "mycloudprinter";
//    private static String password = "mycloud123";

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

    public static synchronized void closeConnection(){
        try {
            if(connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public synchronized ResultSet executeQuery(String sql) throws SQLException {
        ResultSet rs = null;
        if (connection != null) {
            rs= connection.createStatement().executeQuery(sql);
        }
        return rs;
    }

    public synchronized int executeUpdate(String sql) throws SQLException {
        if (connection != null) {
            return connection.createStatement().executeUpdate(sql);
        }
        return 0;
    }

    private DBConnection() {}
}
