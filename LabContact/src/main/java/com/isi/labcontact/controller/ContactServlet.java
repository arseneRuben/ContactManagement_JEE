package com.isi.labcontact.controller;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.manager.ContactManager;
import java.io.IOException;
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
        if (req.getParameter("nom") != null) {
            this.index(req, resp);
        } else {
            this.show(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
}
