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
public class ContactManager extends Manager {

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
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        String query = "select * from contacts;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                contacts.add(new Contact(id, name));
            }
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }

    public static Contact findById(int id) {
        Contact output = null;
        String query = "SELECT * FROM contacts WHERE id = ?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if (result.next() == true) {
                String name = result.getString("name");
                output = new Contact(id, name);
                output.setEmails(EmailManager.findByContact(id));
                // output.setPhoneNumbers(EmailManager.findByContact(id));

            }
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }

    public static int update(Contact c) {
        int result = -1;
        String query = "UPDATE  contacts SET name=? WHERE id=?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setString(1, c.getName());
            ps.setInt(2, c.getId());
            result = ps.executeUpdate();
            ArrayList<Email> fromBd = EmailManager.findByContact(c.getId());
            //deletion of instances of emails retalted to  the contact  in the database
            for (Email email : fromBd) {
                EmailManager.delete(email);
            }
            if (!c.getEmails().isEmpty()) {
                // inserting the new email list into the database
                for (Email email : c.getEmails()) {
                    EmailManager.insert(email);
                }
            }

            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static int delete(Contact c) {
        int result = -1;
        String query = "DELETE FROM  contacts WHERE id=?;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setInt(1, c.getId());
            result = ps.executeUpdate();
            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int insert(Contact c) {
        int result = -1;
        String query = "INSERT INTO contacts(name) VALUES(?) ;";
        try {
            Connection connection = Manager.getConnection();
            PreparedStatement ps = Manager.getPreparedStatement(connection, query);
            ps.setString(1, c.getName());
            result = ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys(); // retourne les clés autogénérées par la base de données
            while (rs.next()) {
                result = rs.getInt(1);
            }

            Manager.closeConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String... args) {
        /*ArrayList<Contact> contacts = ContactManager.findAll();
        for(Contact c : contacts){
            System.out.println(c);
        }*/

 /*Contact c = ContactManager.findById(1);
        System.out.println(c);
         */
        Contact c = new Contact("Francois Capone");

        int idNewContact = ContactManager.insert(c);

        ArrayList<Email> emails = new ArrayList<>();
        emails.add(new Email("francois.capone@isi-mtl.com", idNewContact, EmailType.PERSONNAL));

        for (Email e : emails) {
            EmailManager.insert(e);
        }

        c.setId(idNewContact);

        c.setName("Arsene");

        int nbRows = ContactManager.update(c);

        System.out.println("nbRows updated : " + nbRows);

        nbRows = ContactManager.delete(c);

        System.out.println("nbRows deleted : " + nbRows);

    }

}
