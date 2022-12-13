<%-- 
    Document   : index
    Created on : Nov. 28, 2022, 5:40:51 p.m.
    Author     : isi
--%>

<%@page import="com.isi.labcontact.controller.ContactServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.isi.labcontact.entity.Contact"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%
    List<Contact> listContacts = (ArrayList<Contact>) request.getAttribute("contacts");
//    Contact contactA = new Contact(1, "a");
//    Contact contactB = new Contact(2, "b");
//    Contact contactC = new Contact(3, "c");
//    listContacts.add(contactA);
//    listContacts.add(contactB);
//    listContacts.add(contactC);

//    if (session != null) {
//        listContacts = (ArrayList<Contact>) session.getAttribute("contacts");
//    }
//            (List<Contact>) session.getAttribute("contacts");
//    Contact unContatc = (Contact) session.getAttribute("contacts");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>contacts</title>
    </head>
    <body>
        <h1>contacts</h1>
        <p> Liste de contacts  <a href='contact'>Ajouter un contact</a></p>
        <%if (listContacts != null) {%>        
        <% for (Contact c : listContacts) {%>

        <div>
            <a href="informations?id=<%=c.getId()%>"> - <%=c.getName()%></a><a href='contact?id=<%=c.getId() %>'>Modifier</a>
            <form method="post" action="contact">
                <input type="hidden" name="action" value="deleteContact" />
                <input type="hidden" name="contactId" value="<%= c.getId() %>" />
                <input type="submit" value="Supprimer" />
            </form>
        </div>
        <%}%>

        <%} else {%>
        <div>
            <span>on recois rien.</span>
        </div>
        <% }%>

    </body>
</html>