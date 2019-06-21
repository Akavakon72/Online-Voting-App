package org.btv.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionClass2 {

    public static Connection con;
    public static ResultSet rs;
    public static Statement st;
//connection url,user name & pass
    public static String url = "jdbc:mysql://localhost:3306/voting";
    public static String user = "root";
    public static String pass = "";
    static String qry = "";

    public ConnectionClass2() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Driver Loaded");
            con = DriverManager.getConnection(url, user, pass);
            st = con.createStatement();
//            System.out.println("Database Connect");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ConnectionClass2();
    }
}
