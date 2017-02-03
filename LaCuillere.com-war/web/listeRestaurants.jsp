<%-- 
    Document   : listeRestaurants.jsp
    Created on : 3 févr. 2017, 19:14:35
    Author     : pitit
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Restaurants"%>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des Restaurants</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="styles/lacuillere.css" rel="stylesheet">
        
        <script src="bootstrap/js/jquery.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="javascript/lacuillere.js"></script>
        <script src="javascript/validator.js"></script>
    </head>
    <body>
        <h1 id="mesRestaurants">Liste des restaurants</h1>
         <%
                    HttpSession currentSession = request.getSession();
                    if(currentSession.getAttribute("listeRestaurants") != null){
                      ArrayList<Restaurants> listeRestaurants = (ArrayList<Restaurants>)currentSession.getAttribute("listeRestaurants");
                      Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
                            %>
                            <div class="liste-restaurants">
                            <ul>
                            <%
                                for(Restaurants restaurant : listeRestaurants){
                %>
                                    <li>
                                        <div class="image-restaurant"><a target="_blank" href=""><img src=""/></a></div>
                                        <div class="information-restaurant">
                                            <h3 class="titre-restaurant"><a target="_blank" href="informationsRestaurant.jsp?idRestaurant=<%  out.print(restaurant.getIdRestaurant()); %>"><% out.print(restaurant.getNom()); %></a></h3>
                                            <div class="categorie-restaurant">
                                                <% out.print(restaurant.getFkIdCategorie().getIntitule()); %>
                                            </div>
                                            <div class="adresse-restaurant">
                                                <% out.print(restaurant.getFkIdAdresse().getNumeroRue() + " " + restaurant.getFkIdAdresse().getNomRue() + " " + restaurant.getFkIdAdresse().getCodePostal()+ " " + restaurant.getFkIdAdresse().getVille()); %>
                                            </div>
                                        </div>
                                        <div class="note-restaurant"></div>
                                    </li>
                        <% 
                            }
                        %>
                            </ul>
                    <%
                    }
                    else{  %>
                        <h2>Aucun restaurant trouvé</h2>
                    <%
                    }
                %>
        </div>
    </body>
</html>
