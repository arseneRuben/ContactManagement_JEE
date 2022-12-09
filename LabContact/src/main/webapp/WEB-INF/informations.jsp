<%-- 
    Document   : informations
    Created on : Dec. 6, 2022, 12:15:55 p.m.
    Author     : isi
--%>

<%@page import="com.isi.labcontact.entity.Email"%>
<%@page import="com.isi.labcontact.entity.PhoneNumber"%>
<%@page import="com.isi.labcontact.entity.Contact"%>
<%
    Contact c = (Contact)request.getAttribute("contact");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%= c.getName() %></h1>
        
        <div><a href='contacts'>Retour<a/></div>
        <h2>Téléphones</h2>
        <% for(PhoneNumber pn : c.getPhoneNumbers()) { %>
        <div>
            <span><%=pn.getNumber()%></span>
        </div>
        <% } %>
        
        <h2>Courriels</h2>
        <% for(Email e : c.getEmails()) { %>
        <div>
            <span><%=e.getAdress()%> (<%=e.getType().getLabel() %>)</span>
        </div>
        <% } %>
    </body>
</html>
