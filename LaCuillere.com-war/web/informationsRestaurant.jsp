<%-- 
    Document   : infosRestaurant
    Created on : 3 févr. 2017, 14:15:50
    Author     : pitit
--%>

<%@page import="java.util.ArrayList"%>
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
                if(utilisateur.getEstClient()){
                    if(currentSession.getAttribute("listeRestaurants") != null){
                      ArrayList<Restaurants> listeRestaurants = (ArrayList<Restaurants>)currentSession.getAttribute("listeRestaurants");
                      for(Restaurants restaurant : listeRestaurants){
                          if(restaurant.getIdRestaurant().equals(idRestaurant)){
                            Restaurants restaurantChoisi = restaurant;
                            %>
                                <h1 id="#mesRestaurants"><% out.print(restaurantChoisi.getNom()); %></h1>
                                <div><% out.print(restaurantChoisi.getFkIdAdresse().getNumeroRue()+" "+ restaurantChoisi.getFkIdAdresse().getNomRue()+ " "+ restaurantChoisi.getFkIdAdresse().getCodePostal()+ " "+ restaurantChoisi.getFkIdAdresse().getVille()); %></div>
                                <div><% out.print(restaurantChoisi.getFkIdCategorie().getIntitule()); %></div>
                                <div>
                                    <button type="button">Reserver une table</button>
                                </div>
                            <%
                          }
                      }
                    }
                }
                else{
                    Collection<Restaurants> restaurantsCollection = (Collection<Restaurants>)utilisateur.getRestaurantsCollection();
                    for(Restaurants restaurant : restaurantsCollection){
                        if(restaurant.getIdRestaurant().equals(idRestaurant)){
                            Restaurants restaurantChoisi = restaurant;
            %>
                            <h1 id="#mesRestaurants"><% out.print(restaurantChoisi.getNom()); %></h1>
                                <div><% out.print(restaurantChoisi.getFkIdAdresse().getNumeroRue()+" "+ restaurantChoisi.getFkIdAdresse().getNomRue()+ " "+ restaurantChoisi.getFkIdAdresse().getCodePostal()+ " "+ restaurantChoisi.getFkIdAdresse().getVille()); %></div>
                                <div><% out.print(restaurantChoisi.getFkIdCategorie().getIntitule()); %></div>
            <%
                        }
                    }
                }
            }
        %>
    </body>
</html>
