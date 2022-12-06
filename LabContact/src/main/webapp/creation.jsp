<%-- 
    Document   : creation
    Created on : Dec 6, 2022, 12:37:26 PM
    Author     : isi
--%>

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
        
        <button>Enregistrer</button>
        <button>Retour</button>
        
        <input type='text' id="nom" placeholder="Nom">
        
        <h3>Téléphones</h3>
        <input type="number" id="numero" placeholder="Numéro">
        <select name="typeTel" id="typeTel">
             <option value="">type</option>
             <option value="maison">Maison</option>
             <option value="travail">Travail</option>
             <option value="cellulaire">Cellulaire</option>
        </select>
        <button>ajouter</button>
        
        <h3>Courriels</h3>
        <input type="email" id="email" placeholder="Courriel" pattern=".+@globex\.com" size="30">
        <select name="typeCourriel" id="typeCourriel">
             <option value="">type</option>
             <option value="personnel">Personnel</option>
             <option value="professionnel">Professionnel</option>
        </select>
        <button>ajouter</button>
    </body>
</html>
