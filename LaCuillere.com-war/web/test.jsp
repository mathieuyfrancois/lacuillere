<%-- 
    Document   : test
    Created on : 24 janv. 2017, 12:43:31
    Author     : pitit
--%>

<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            HttpSession currentSession = request.getSession();
            Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
            out.println(utilisateur.getNom()+ " " + utilisateur.getPrenom());
        %>
    </body>
</html>
