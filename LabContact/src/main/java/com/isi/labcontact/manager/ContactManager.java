/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import com.isi.labcontact.entity.PhoneNumber;
import com.isi.labcontact.type.EmailType;
import com.isi.labcontact.type.PhoneNumberType;
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
public class ContactManager extends Manager {

    private static ArrayList<Contact> contacts = new ArrayList<Contact>();

    /*
    static {
        Contact herzt = new Contact(1, "Alain Herzt");
        herzt.getEmails().add(new Email("alain.herzt@laboanimal.ca", 1, EmailType.PROFESSIONNAL));
        herzt.getEmails().add(new Email("burno.agard@laboanimal.ca", 1, EmailType.PROFESSIONNAL));
        herzt.getPhoneNumbers().add(new PhoneNumber("456987147", 1, PhoneNumberType.CELL));
        herzt.getPhoneNumbers().add(new PhoneNumber("456983347", 1, PhoneNumberType.WORK));

        Contact tanenbaum = new Contact(2, "Andrew Tanenbaum");
        tanenbaum.getEmails().add(new Email("agnes.billon@laboanimal.ca", 2, EmailType.PERSONNAL));
        tanenbaum.getEmails().add(new Email("arielle.billon@laboanimal.ca", 2, EmailType.PROFESSIONNAL));
        tanenbaum.getPhoneNumbers().add(new PhoneNumber("8896987147", 1, PhoneNumberType.CELL));
        tanenbaum.getPhoneNumbers().add(new PhoneNumber("9236587777", 1, PhoneNumberType.HOUSE));
        //Contact linux = new Contact("Andrew Tanenbaum");
        //Contact bonde = new Contact("Bonde Lossan");

        contacts.add(herzt);
        contacts.add(tanenbaum);

    }
     */
    public static ArrayList<Contact> findAll() {
        String query = "select * from contacts;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                contacts.add(new Contact(id, name));
            }
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }

    public static Contact findById(int id) {
        Contact output = null;
        String query = "SELECT * FROM contacts WHERE id = ?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next() == true) {
                String name = result.getString("name");
                output = new Contact(id, name);
                output.setEmails(EmailManager.findByContact(id));
               // output.setPhoneNumbers(EmailManager.findByContact(id));

            }
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    public static void main(String args[]) {
        // Contact c = ContactManager.findById(1);

        System.out.print(ContactManager.findById(1));

    }

    public static int update(Contact c) {
        int result = -1;
        String query = "UPDATE  contacts SET name=? WHERE id=?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getId());
            result = ps.executeUpdate();
            if (!c.getEmails().isEmpty()) {
                Contact fromBd = ContactManager.findById(c.getId());
                //deletion of instances of emails retalted to  the contact  in the database
                for (Email email : fromBd.getEmails()) {
                    EmailManager.delete(email);
                }
                // inserting the new email list into the database
                for (Email email : c.getEmails()) {
                    EmailManager.insert(email);
                }
            }

            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int delete(Contact c) {
        int result = -1;
        String query = "DELETE FROM  contacts WHERE id=?;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, c.getId());
            result = ps.executeUpdate();
            if (connexion != null) {
                connexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static int insert(Contact c) {
        int result = -1;
        String query = "INSERT  INTO  contacts(id, name)  VALUES  id=?, name=? ;";
        try {
            connexion = DriverManager.getConnection(urlServeur, username, password);
            PreparedStatement ps = connexion.prepareStatement(query);
            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
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
