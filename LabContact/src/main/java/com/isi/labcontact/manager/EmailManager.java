/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import static com.isi.labcontact.manager.Manager.connexion;
import static com.isi.labcontact.manager.Manager.password;
import static com.isi.labcontact.manager.Manager.urlServeur;
import static com.isi.labcontact.manager.Manager.username;
import com.isi.labcontact.type.EmailType;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author isi
 */
public class EmailManager extends Manager {

    private static ArrayList<Email> items = new ArrayList<>();

    public static ArrayList<Email> findAll() {
        String query = "select * from emails;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String address = result.getString("address");
                int contact_id = result.getInt("contact_id");
                EmailType type = Email.getType(result.getString("type"));
                items.add(new Email(id, address, type, contact_id));
            }
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public static ArrayList<Email> findByContact(int contact_id) {
        String query = "SELECT * FROM emails WHERE contact_id = ?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, contact_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String address = result.getString("address");
                EmailType type = Email.getType(result.getString("type"));
                items.add(new Email(id, address, type, contact_id));
            }
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public static Contact findById(int id) {
        EmailManager.items = EmailManager.findAll();
        Contact output = null;
        Iterator it = EmailManager.items.iterator();
        while (it.hasNext()) {
            output = (Contact) it.next();
            if (output.getId() == id) {
                break;
            }
        }
        return output;
    }

    public static int delete(Email email) {
        int result = -1;
        String query = "DELETE FROM  emails WHERE id=?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, email.getId());
            result = ps.executeUpdate();
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int update(Email email) {
        int result = -1;
        String query = "UPDATE  emails SET address=?, contact_id=?, type=? WHERE id=?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setString(1, email.getAdress());
            ps.setInt(2, email.getContactId());
            ps.setString(3, email.getType().getLabel());
            ps.setInt(4, email.getId());
            result = ps.executeUpdate();
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int insert(Email email) {
        int result = -1;
        String query = "INSERT  INTO  email(id, address, contact_id, type)  VALUES  (?, ?, ?, ?) ;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, email.getId());
            ps.setString(2, email.getAdress());
            ps.setInt(3, email.getContactId());
            ps.setString(4, email.getType().getLabel());
            result = ps.executeUpdate();
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
