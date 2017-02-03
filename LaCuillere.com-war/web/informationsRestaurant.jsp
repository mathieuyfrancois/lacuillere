<%-- 
    Document   : infosRestaurant
    Created on : 3 févr. 2017, 14:15:50
    Author     : pitit
--%>

<%@page import="java.util.Collection"%>
<%@page import="Entity.Restaurants"%>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  HttpSession currentSession = request.getSession();
            Integer idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
            if(currentSession.getAttribute("utilisateur") != null){
                Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
                Collection<Restaurants> restaurantsCollection = (Collection<Restaurants>)utilisateur.getRestaurantsCollection();
                for(Restaurants restaurant : restaurantsCollection){
                    if(restaurant.getIdRestaurant().equals(idRestaurant)){
                        Restaurants restaurantChoisi = restaurant;
        %>
                        <h1><% out.print(restaurantChoisi.getNom()); %></h1>
                        <%
                    }
                }
                if(utilisateur.getEstClient()){
                    
                    %>
                   
                    <%
                }
                else { %>
                    <ul>
                        <li>
                            <div class="menus-restaurant">
                                <h3 class="titre-restaurant"><a target="_blank" href="informationsRestaurant.jsp?idRestaurant=<%  out.print(restaurant.getIdRestaurant()); %>"><% out.print(restaurant.getNom()); %></a></h3>
                                <div class="categorie-restaurant">
                                    <% out.print(""); %>
                                </div>
                                <div class="adresse-restaurant">
                                    <% out.print(""); %>
                                </div>
                            </div>
                            <div class="note-restaurant"></div>
                        </li>
                    </ul>
                <%
                }
            }
        %>
        
    </body>
</html>
