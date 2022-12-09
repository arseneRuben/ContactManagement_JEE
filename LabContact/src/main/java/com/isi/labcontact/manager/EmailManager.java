/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import com.isi.labcontact.type.EmailType;
import java.sql.Connection;
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

    

    public static ArrayList<Email> findAll() {
        ArrayList<Email> items = new ArrayList<>();
        String query = "select * from emails;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String address = result.getString("address");
                int contact_id = result.getInt("contact_id");
                EmailType type = Email.getType(result.getString("type"));
                items.add(new Email(id, address, type, contact_id));
            }
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public static ArrayList<Email> findByContact(int contact_id) {
        ArrayList<Email> items = new ArrayList<>();
        String query = "SELECT * FROM emails WHERE contact_id = ?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setInt(1, contact_id);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String address = result.getString("address");
                EmailType type = Email.getType(result.getString("type"));
                items.add(new Email(id, address, type, contact_id));
            }
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    /*public static Contact findById(int id) {
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
    }*/

    public static int delete(Email email) {
       
        int result = -1;
        String query = "DELETE FROM  emails WHERE id=?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setInt(1, email.getId());
            result = ps.executeUpdate();
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int update(Email email) {
        
        int result = -1;
        String query = "UPDATE  emails SET address=?, contact_id=?, type=? WHERE id=?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setString(1, email.getAdress());
            ps.setInt(2, email.getContactId());
            ps.setString(3, email.getType().getLabel());
            ps.setInt(4, email.getId());
            result = ps.executeUpdate();
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int insert(Email email) {
        
        int result = -1;
        String query = "INSERT  INTO  emails(address, contact_id, type)  VALUES  (?, ?, ?) ;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);            
            ps.setString(1, email.getAdress());
            ps.setInt(2, email.getContactId());
            ps.setString(3, email.getType().getLabel());
            result = ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                result = rs.getInt(1);
            }            
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmailManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static void main(String ... args){
        /*ArrayList<Email> emails = EmailManager.findAll();
        
        for(Email e : emails){
            System.out.println(e);
        
        }*/
        
        /*ArrayList<Email> emails = EmailManager.findByContact(12);
        
        for(Email e : emails){
            System.out.println(e);        
        }*/
        
        Email email = new Email("toto@toto.com", 12, EmailType.PERSONNAL);
        
        int newEmailId = EmailManager.insert(email);        
        
        email.setId(newEmailId);
        
        System.out.println("id newEmail = " + newEmailId);
        
        email.setAdress("titi@titi.com");
        
        int nbRows = EmailManager.update(email);
        
        System.out.println("nbRows Updated = " + nbRows);
        
        nbRows = EmailManager.delete(email);
        
        System.out.println("nbRows Deleted = " + nbRows);
        
    }
}
