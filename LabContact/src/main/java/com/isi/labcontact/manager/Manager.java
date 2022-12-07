/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author isi
 */
public abstract class Manager {

    protected static String dbName="contactdb";
    protected static String urlServeur = "jdbc:mariadb://localhost:3306/" + dbName;
    protected static String username = "root";
    protected static String password = "abc123...";
    protected static Connection connexion;
    protected  PreparedStatement statement;
    protected  ResultSet result;
    protected String query;

}
