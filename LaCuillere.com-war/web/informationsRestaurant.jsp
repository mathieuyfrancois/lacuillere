<%-- 
    Document   : infosRestaurant
    Created on : 3 févr. 2017, 14:15:50
    Author     : pitit
--%>

<%@page import="Entity.Reductions"%>
<%@page import="Entity.PlatsMenus"%>
<%@page import="Entity.Plats"%>
<%@page import="Entity.Menus"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collection"%>
<%@page import="Entity.Restaurants"%>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations Restaurant</title>
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
                                    <button type="button" class="btn btn-primary">Reserver une table</button>
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
                            <div class="menu">
                                <h3>Menus <a href="#" data-toggle="modal" data-target="#menuModal" onclick=""><button type="button" class="btn btn-primary">Ajouter un menu</button></a></h3>
                                <% if(!restaurantChoisi.getMenusCollection().isEmpty()){        
                                    for(Menus menu : restaurantChoisi.getMenusCollection()){
                                %>
                                <div class="contenu-menu">
                                    <h4><% menu.getNom(); %></h4>
                                    <br/>
                                    <%
                                        if(!menu.getPlatsMenusCollection().isEmpty()){        
                                            for(PlatsMenus platMenu : menu.getPlatsMenusCollection()){
                                                %>
                                                <div class="nom-plat"><% platMenu.getFkIdPlat().getNom(); %></div>
                                                <div class="description-plat"><% platMenu.getFkIdPlat().getDescription(); %></div>
                                                <%
                                            }
                                        }
                                        %>   
                                </div>
                                <%
                                    }
                                } %>
                            </div>
            <%
                        }
                    }
                }
            }
        %>
        
        <div class="modal fade" id="menuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Ajouter un menu</h1><br>
                    <form action="AjouterMenu" method="post" id="menuForm" role="form" data-toggle="validator">
                        <fieldset id="ajoutMenuFieldset" class="modal-inputs">
                            <div class="form-group has-feedback">
                                <div class="input-group">
                                    <input type="text" name="nomMenu" class="form-control" id="inputNomMenu" placeholder="Nom Menu" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="input-group">
                                    <input type="text" name="prix" class="form-control" id="inputPrixMenu" placeholder="Prix" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                                </div>
                                <div class="input-group">
                                    <select class="selectpicker form-control" title="Reduction" name="reduction" required>
                                        <%
                                            if(currentSession.getAttribute("utilisateur") != null){
                                                ArrayList<Reductions> reductionsList = (ArrayList<Reductions>)currentSession.getAttribute("reductionsList");
                                                for (Reductions reduction : reductionsList){
                                                    out.print("<option value='" + reduction.getIdReduction()+ "'>" + reduction.getReduction()+ "% </option>");
                                                }
                                            }
                                        %>
                                    </select>
                                </div>
                                <fieldset id="ajoutPlatFieldset" class="modal-inputs">
                                    <legend>Entree</legend>
                                    <div class="input-group">
                                        <input type="text" name="nomEntree" class="form-control" id="inputNomPlat" placeholder="Nom Plat" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="input-group">
                                        <input type="text" name="descriptionEntree" class="form-control" id="inputDescriptionPlat" placeholder="Description Plat">
                                    </div>
                                </fieldset>
                                <fieldset id="ajoutPlatFieldset" class="modal-inputs">
                                    <legend>Plat</legend>
                                    <div class="input-group">
                                        <input type="text" name="nomPlat" class="form-control" id="inputNomPlat" placeholder="Nom Plat" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="input-group">
                                        <input type="text" name="descriptionPlat" class="form-control" id="inputDescriptionPlat" placeholder="Description Plat">
                                    </div>
                                </fieldset>
                                <fieldset id="ajoutPlatFieldset" class="modal-inputs">
                                    <legend>Dessert</legend>
                                    <div class="input-group">
                                        <input type="text" name="nomDessert" class="form-control" id="inputNomPlat" placeholder="Nom Plat" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                    </div>
                                    <div class="input-group">
                                        <input type="text" name="descriptionDessert" class="form-control" id="inputDescriptionPlat" placeholder="Description Plat">
                                    </div>
                                </fieldset>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="validerMenu" class="btn btn-primary" value="Valider">
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        
    </body>
</html>
