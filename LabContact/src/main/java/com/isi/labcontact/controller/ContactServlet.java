package com.isi.labcontact.controller;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import com.isi.labcontact.entity.PhoneNumber;
import com.isi.labcontact.manager.ContactManager;
import com.isi.labcontact.manager.EmailManager;
import com.isi.labcontact.type.EmailType;
import com.isi.labcontact.type.PhoneNumberType;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contacts", "/informations", "/contact"})
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idString = null;
        switch (req.getServletPath()) {
            case "/contacts":
                ArrayList<Contact> contacts = ContactManager.findAll();
                req.setAttribute("contacts", contacts);
                req.getRequestDispatcher("WEB-INF/contacts.jsp").forward(req, resp);
                break;
            case "/informations":
                idString = req.getParameter("id");
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    Contact c = ContactManager.findById(id);
                    req.setAttribute("contact", c);
                    req.getRequestDispatcher("WEB-INF/informations.jsp").forward(req, resp);
                } else {
                    resp.sendRedirect("error404.html");
                }
                break;
            case "/contact":
                idString = req.getParameter("id");
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    Contact c = ContactManager.findById(id);
                    if (c != null) {
                        HttpSession session = req.getSession(true);
                        if (session != null) {
                            session.setAttribute("contact", c);
                            req.getRequestDispatcher("WEB-INF/contact.jsp").forward(req, resp);
                        }
                    } else {
                        resp.sendRedirect("error404.html");
                    }
                } else {
                    HttpSession session = req.getSession(true);
                    if (session != null) {
                        Contact c = new Contact("");
                        session.setAttribute("contact", c);
                        req.getRequestDispatcher("WEB-INF/contact.jsp").forward(req, resp);
                    }

                }
                break;
            default:
                throw new AssertionError();
        }

        /*String action = req.getParameter("action");

        // afficher tous les contacts
        // afficher un contact par id
        // modifier un contact par id
        // ajouter un nouveau contact
        if (req.getParameter("nom") != null) {
            this.index(req, resp);
        } else {
            this.show(req, resp);
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("deleteContact".equals(action)) {
            //supprimer un contact par id

            String contactId = request.getParameter("contactId");
            if(contactId != null) {
                int id = Integer.parseInt(contactId);
                EmailManager.deleteByContactId(id);
                ContactManager.delete(id);
            }            
            response.sendRedirect("contacts");
            
        } else if ("addEmail".equals(action)) {

            String emailErrorMessage = "";
            HttpSession session = request.getSession(true);
            if (session != null) {
                Contact c = (Contact) session.getAttribute("contact");
                String emailInput = request.getParameter("email");
                String typeCourriel = request.getParameter("typeCourriel");
                EmailType type = null;
                switch (typeCourriel) {
                    case "PERSONNAL":
                        type = EmailType.PERSONNAL;
                        break;
                    case "PROFESSIONNAL":
                        type = EmailType.PROFESSIONNAL;
                        break;
                    default:
                        emailErrorMessage = "Select a valid email type.";
                }

                if (emailErrorMessage.equals("")) {
                    Email email = new Email(emailInput, c.getId(), type);
                    c.addEmail(email);
                    session.setAttribute("contact", c);
                }

                request.setAttribute("emailErrorMessage", emailErrorMessage);
                request.getRequestDispatcher("WEB-INF/contact.jsp").forward(request, response);
            }

        } else if ("deleteEmail".equals(action)) {
            HttpSession session = request.getSession(true);
            if (session != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Contact c = (Contact) session.getAttribute("contact");

                List<Email> emails = c.getEmails();

                Iterator<Email> iter = emails.iterator();
                boolean found = false;
                while (iter.hasNext() && !found) {
                    Email e = iter.next();
                    if (e.getId() == id) {
                        iter.remove();
                        found = true;
                    }
                }

                c.setEmails(emails);

                session.setAttribute("contact", c);
                request.getRequestDispatcher("WEB-INF/contact.jsp").forward(request, response);
            }
        } else if ("saveContact".equals(action)) {
            HttpSession session = request.getSession(true);
            if (session != null) {
                Contact c = (Contact) session.getAttribute("contact");
                if (c.getId() == 0) {
                    int idGerere = ContactManager.insert(c);
                    c = ContactManager.findById(idGerere);
                    session.setAttribute("contact", c);
                    
                    //response.sendRedirect("contacts");
                } else {
                    ContactManager.update(c);                    
                }
                request.getRequestDispatcher("WEB-INF/contact.jsp").forward(request, response);

            }
        } else if ("updateName".equals(action)) {
            HttpSession session = request.getSession(true);
            if (session != null) {
                Contact c = (Contact) session.getAttribute("contact");
                String name = request.getParameter("nom");
                c.setName(name);
                session.setAttribute("contact", c);
                request.getRequestDispatcher("WEB-INF/contact.jsp").forward(request, response);
            }
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
        HttpSession session = req.getSession(true);
        Contact contact = (Contact) session.getAttribute("contact");

        String name = req.getParameter("nom");
        contact.setName(name);
        contact.setEmails((ArrayList<Email>) session.getAttribute("emails"));
        contact.setPhoneNumbers((ArrayList<PhoneNumber>) session.getAttribute("phones"));

    }

    protected void addEmail(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);

        Contact contact = (Contact) session.getAttribute("contact");
        ArrayList<Email> emails = (ArrayList<Email>) contact.getEmails();

        String email = req.getParameter("courriel");
        String emailType = req.getParameter("typeCourriel");
        EmailType type = null;
        switch (emailType.toUpperCase()) {
            case "PERSONNAL":
                type = EmailType.PERSONNAL;
                break;
            case "PROFESSIONNAL":
                type = EmailType.PROFESSIONNAL;
                break;
        }
        Email e = new Email(email, contact.getId(), type);
        emails.add(e);
        contact.setEmails(emails);
        session.setAttribute("contact", contact);
    }

    protected void addPhoneNumber(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(true);

        Contact contact = (Contact) session.getAttribute("contact");
        // PhoneNumber phoneNumber;
        ArrayList<PhoneNumber> phones = (ArrayList<PhoneNumber>) contact.getPhoneNumbers();
        //phones.add(phoneNumber);
        //  contact.setPhoneNumbers(phones);
        // session.setAttribute("contact", contact);

        String phoneNumber = req.getParameter("phone");
        String phoneNumberType = req.getParameter("typePhoneNumber");
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
        PhoneNumber phone = new PhoneNumber(phoneNumber, contact.getId(), type);
        phones.add(phone);
        contact.setPhoneNumbers(phones);
        session.setAttribute("contact", contact);
    }
}
