package com.isi.labcontact.controller;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import com.isi.labcontact.entity.PhoneNumber;
import com.isi.labcontact.manager.ContactManager;
import com.isi.labcontact.type.EmailType;
import com.isi.labcontact.type.PhoneNumberType;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contactServlet"})
public class ContactServlet extends HttpServlet {

 

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        // afficher tous les contacts
        // afficher un contact par id
        // modifier un contact par id
        // ajouter un nouveau contact
        if (req.getParameter("nom") != null) {
            this.index(req, resp);
        } else {
            this.show(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("deleteContact".equals(action)) {
            //supprimer un contact par id

        } else if ("editContactName".equals(action)) {
            //ajouter le nom du contact dans la session
        }

        //ajouter un email dans la session
        //ajouter un telephone dans la session        
        //sauvegarder le contact en session dans la base de donnees
        String name = request.getParameter("nom");
        String email = request.getParameter("courriel");
        String emailType = request.getParameter("typeCourriel");
        String phoneType = request.getParameter("typePhone");

    }

    protected void index(HttpServletRequest req, HttpServletResponse resp) {
        if (ContactManager.findAll() != null) {
            List<Contact> contacts;
            HttpSession session = req.getSession(true);
            contacts = ContactManager.findAll();
            session.setAttribute("contacts", contacts);
            try {
                req.getRequestDispatcher("contact.jsp").forward(req, resp);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    protected void show(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("contact", ContactManager.findById(Integer.parseInt(req.getParameter("id"))));
        try {
            req.getRequestDispatcher("contact.jsp").forward(req, resp);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void updateContact(HttpServletRequest req, HttpServletResponse resp) {
        int id = parseInt(req.getParameter("id"));
        String name = req.getParameter("nom");
        String email = req.getParameter("courriel");
        String emailType = req.getParameter("typeCourriel");
        String phoneType = req.getParameter("typePhone");
        Contact contact = new Contact(id, name, "", "");
    }

    protected void addPhoneNumber(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        List<Email> emails = new ArrayList<>(); 
        int emailId = parseInt(req.getParameter("id"));
        String email = req.getParameter("courriel");
        String emailType = req.getParameter("typeCourriel");
        int contactId = 2;
        EmailType type = null;
        switch (emailType.toUpperCase()) {
            case "PERSONNAL":
                type = EmailType.PERSONNAL;
                break;
            case "PROFESSIONNAL":
                type = EmailType.PROFESSIONNAL;
                break;
        }
        Email e = new Email(emailId, email, contactId, type);
        emails.add(e);
        session.setAttribute("emails",emails);
    }

    protected void addEmail(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);
        List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
        int phoneNumberId = parseInt(req.getParameter("id"));
        String phoneNumber = req.getParameter("phone");
        String phoneNumberType = req.getParameter("typePhoneNumber");
        int contactId = 2;
        PhoneNumberType type = null;
        switch (phoneNumberType.toUpperCase()) {
            case "WORK":
                type = PhoneNumberType.WORK;
                break;
            case "HOUSE":
                type = PhoneNumberType.HOUSE;
                break;
            case "CELL":
                type = PhoneNumberType.CELL;
                break;
        }
        PhoneNumber phone = new PhoneNumber(phoneNumber, contactId, type);
        phoneNumbers.add(phone);
        session.setAttribute(";phoneNumbers",phoneNumbers);
    }
}
