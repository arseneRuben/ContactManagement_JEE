package com.isi.labcontact.controller;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.manager.ContactManager;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContactServlet", urlPatterns = {"/contactServlet"})
public class ContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           List<Contact> contacts;
           if(ContactManager.findAll()!=null){
               contacts=ContactManager.findAll();
               request.setAttribute("contacts", contacts); 
               request.getRequestDispatcher("contact.jsp").forward(request, response);
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

}
