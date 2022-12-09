/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import com.isi.labcontact.entity.Contact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author isi
 */
public abstract class Manager {
    protected static String dbName="contactdb";
    protected static String urlServeur = "jdbc:mariadb://localhost:3310/" + dbName;
    protected static String username = "root";
    protected static String password = "abc123...";
    
    protected static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("org.mariadb.jdbc.Driver");
        return DriverManager.getConnection(urlServeur, username, password);
    }
    
    protected static PreparedStatement getPreparedStatement(Connection connection, String query) throws SQLException{
        return connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    }
    
    protected static void closeConnection(Connection connection) throws SQLException{
        if(connection != null){
            connection.close();
        }
    }
    
}
