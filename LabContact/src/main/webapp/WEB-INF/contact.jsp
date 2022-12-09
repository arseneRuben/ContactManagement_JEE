<%-- 
    Document   : creation
    Created on : Dec 6, 2022, 12:37:26 PM
    Author     : isi
--%>


<%@page import="com.isi.labcontact.type.EmailType"%>
<%@page import="com.isi.labcontact.entity.Email"%>
<%@page import="com.isi.labcontact.type.PhoneNumberType"%>
<%@page import="com.isi.labcontact.entity.PhoneNumber"%>
<%@page import="com.isi.labcontact.entity.Contact"%>
<%
    Contact c = (Contact) session.getAttribute("contact");

%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Création</title>
    </head>
    <body>
        <h2>Contacts > Création</h2>

        <h1>Création d'un contact</h1>

        <form action='contact' method="post">
            <input type="hidden" name="action" value="saveContact" />
            <input type="submit" value="Enregistrer" />
        </form>
        <a href="contacts">Retour</a>

        <input type='text' id="nom" name='nom' placeholder="Nom" value="<%= c.getName()%>" />

        <h3>Téléphones</h3>

        <% for (PhoneNumber pn : c.getPhoneNumbers()) {%>
        <div>
            <input type="number" id="numero" placeholder="Numéro" value="<%= pn.getNumber()%>" />
            <select name="typeTel" id="typeTel">
                <option value="">type</option>
                <option <%= pn.getType().equals(PhoneNumberType.HOUSE) ? "selected" : ""%>  value="<%= PhoneNumberType.HOUSE%>"><%= PhoneNumberType.HOUSE.getLabel() %></option>
                <option <%= pn.getType().equals(PhoneNumberType.WORK) ? "selected" : ""%> value="<%= PhoneNumberType.WORK%>"><%= PhoneNumberType.WORK.getLabel() %></option>
                <option <%= pn.getType().equals(PhoneNumberType.CELL) ? "selected" : ""%> value="<%= PhoneNumberType.CELL%>"><%= PhoneNumberType.CELL.getLabel() %></option>
            </select>
            <button>Supprimer</button>
        </div>
        <% }%>
        <div>
            <input type="number" id="numero" placeholder="Numéro" />
            <select name="typeTel" id="typeTel">
                <option value="">type</option>
                <option value="<%= PhoneNumberType.HOUSE%>">Maison</option>
                <option value="<%= PhoneNumberType.WORK%>">Travail</option>
                <option value="<%= PhoneNumberType.CELL%>">Cellulaire</option>
            </select>
            <button>Ajouter</button>
        </div>


        <h3>Courriels</h3>
        <% for (Email e : c.getEmails()) {%>
        <div>
            <input type="email" id="email" placeholder="Courriel" pattern=".+@globex\.com" size="30" value="<%=e.getAdress() %>" />
            <select name="typeCourriel" id="typeCourriel">
                <option value="">type</option>
                <option <%= e.getType().equals(EmailType.PERSONNAL) ? "selected" : "" %> value="<%= EmailType.PERSONNAL %>"><%= EmailType.PERSONNAL.getLabel() %></option>
                <option <%= e.getType().equals(EmailType.PROFESSIONNAL) ? "selected" : "" %> value="<%= EmailType.PROFESSIONNAL %>"><%= EmailType.PROFESSIONNAL.getLabel() %></option>
            </select>
            <form action="contact" method="post">
                <input type='hidden' name="id" value="<%=e.getId() %>" />
                <input type='hidden' name="action" value="deleteEmail" />
                <input type='submit' value='Supprimer' />
            </form>
        </div>
        <% }%>
        <input type="email" id="email" placeholder="Courriel" pattern=".+@globex\.com" size="30" />
        <select name="typeCourriel" id="typeCourriel">
            <option value="">type</option>
            <option value="<%= EmailType.PERSONNAL %>"><%= EmailType.PERSONNAL.getLabel() %></option>
            <option value="<%= EmailType.PROFESSIONNAL %>"><%= EmailType.PROFESSIONNAL.getLabel() %></option>
        </select>
        <button>ajouter</button>
    </body>
</html>