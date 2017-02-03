<%-- 
    Document   : accueilRestaurateur
    Created on : 28 janv. 2017, 14:10:59
    Author     : pitit
--%>

<%@page import="Entity.Restaurants"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entity.Categories"%>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Accueil Restaurateur</title>
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
    <!-- CONTENU DE LA PAGE -->
        
        <!-- Navbar -->
        <div class="navbar navbar-default navbar-fixed-top cuillere-navbar-resto" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle Navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="accueil.jsp"><div class="logoCuillere"></div></a>	
                </div>
                <%
                    HttpSession currentSession = request.getSession();
                    if(currentSession.getAttribute("utilisateur") != null){
                        Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
                        Boolean estClient= utilisateur.getEstClient();
                        if(!estClient){
                %>
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li class="dropdown cuillere-dropdown-resto">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><%= utilisateur.getPrenom() %>
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Annonces</a></li>
                                        <li>  
                                            <a a href="#" data-toggle="modal" data-target="#restoModal" onclick="clickAjoutRestaurant()" >Ajouter mon restaurant</a>
                                        </li>
                                        <li> <a a href="#mesRestaurants" data-toggle="modal" data-target="#"> Gérer mes restaurants </a></li>
                                        <li><a href="#">Mes informations</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="Deconnexion">Deconnexion</a></li>
                                    <!--    <li><a href="#">Separated link</a></li>
                                        <li><a href="#">One more separated link</a></li>-->
                                    </ul>
                                </li>
                            </ul>
                        </div>
                <% } } else {%>
                        <div class="navbar-collapse collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="#" data-toggle="modal" data-target="#loginModal" onclick="clickSeConnecter()" id="statutConnexion">Se Connecter</a></li>
                            </ul>
                        </div>
                <% } %>

            </div>
        </div>

         <!-- Modal Inscription-->
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Se connecter</h1><br>
                    <form action="Connexion" method="post" id="connexionForm" role="form" data-toggle="validator">
                        <fieldset id="connexionFieldset" class="modal-inputs">
                           <div class="form-group has-feedback">
                               <div class="input-group">
                                    <input type="email" name="email" class="form-control" id="inputEmailConnexion" placeholder="E-mail*" data-error="E-mail invalide" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                               </div>
                            </div>
                            <div class="form-group has-feedback">
                               <div class="input-group">
                                    <input type="password" name="pass" class="form-control" id="inputPassConnexion" placeholder="Mot de passe*" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                               </div>
                            </div>
                            <div class="form-group">
                                <input type="submit" name="validerConnexion" class="login loginmodal-submit btn btn-primary" value="Valider">
                            </div>
                        </fieldset>
                    </form>
                    <div id="keyAndCreate">
                        <div class="login-help">
                              <a href="#">Mot de passe oublié ?</a>
                        </div>
                        <hr/>
                        <div class="create-compte">
                            <button type="button" class="btn btn-success btn-create-compte" onclick="clickCreerCompte()">Créer un compte</button>
                        </div>
                    </div>
                    <form action="Inscription" method="post" id="inscriptionForm" role="form" data-toggle="validator">
                        <fieldset id="inscriptionFieldset" class="modal-inputs">
                            <!-- Input nom -->
                            <div class="form-group has-feedback">
                               <div class="input-group">
                                    <input type="text" name="nom" class="form-control" id="inputNomInscription" placeholder="Nom*" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                               </div>
                            </div>
                            <!-- Input pour savoir qu'on a à faire à un restaurateur -->
                            <input type="hidden" name="client" value="non">
                            <!-- Input prenom -->
                            <div class="form-group has-feedback">
                               <div class="input-group">
                                    <input type="text" name="prenom" class="form-control" id="inputPrenomInscription" placeholder="Prenom*" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                               </div>
                            </div>
                            <!-- Input numero telephone -->
                            <input type="text" name="telephone" placeholder="Numero telephone">
                            <fieldset>
                                <legend>Adresse</legend>
                                <input type="text" name="numeroRue" placeholder="N° rue">
                                <input type="text" name="nomRue" placeholder="Nom rue">
                                <input type="text" name="ville" placeholder="Ville">
                                <input type="text" name="codePostal" placeholder="Code postal">
                            </fieldset>
                            <fieldset>
                                <legend>Identifiants</legend>
                                <div class="form-group has-feedback">
                                   <div class="input-group">
                                        <input type="email" name="email" class="form-control" id="inputEmailInscription" placeholder="E-mail*" data-error="E-mail invalide" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                   </div>
                                </div>
                                <div class="form-group has-feedback">
                                   <div class="input-group">
                                        <input type="password" name="pass" class="form-control" id="inputPassInscription" placeholder="Mot de passe*" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                   </div>
                                </div>
                            </fieldset>
                            <div class="form-group">
                                <input type="submit" name="validerInscription" class="login loginmodal-submit btn btn-primary" value="Valider">
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->


        <!-- Modal Ajouter restaurant-->
        <div class="modal fade" id="restoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="loginmodal-container">
                    <h1>Ajoutez un restaurant</h1><br>
                    <form action="CreerRestaurant" method="post" id="ajouterRestaurantForm" role="form" data-toggle="validator" enctype="multipart/form-data">
                        <fieldset id="ajouterRestaurantFieldset" class="modal-inputs">
                            <!-- Input nom -->
                            <div class="form-group has-feedback">
                               <div class="input-group">
                                    <input type="text" name="nom" class="form-control" id="inputNomInscription" placeholder="Nom restaurant*" required>
                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                    <div class="help-block with-errors"></div>
                               </div>
                            </div>
                              <div class="input-group">
                                        <input type="email" name="email" class="form-control" id="inputEmailInscription" placeholder="E-mail*" data-error="E-mail invalide" required>
                                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                        <div class="help-block with-errors"></div>
                                   </div>
                            <!-- Input numero telephone -->
                            <input type="text" name="telephone" placeholder="Numero telephone">
                            <fieldset>
                                <legend>Adresse</legend>
                                <input type="text" name="numeroRue" placeholder="N° rue">
                                <input type="text" name="nomRue" placeholder="Nom rue">
                                <input type="text" name="ville" placeholder="Ville">
                                <input type="text" name="codePostal" placeholder="Code postal">
                            </fieldset>
                            <select class="selectpicker" title="Catégorie" name="categorie">
                                <%
                                    if(currentSession.getAttribute("utilisateur") != null){
                                        ArrayList<Categories> categoriesList = (ArrayList<Categories>)currentSession.getAttribute("categoriesList");
                                        for (Categories categorie: categoriesList){
                                            out.print("<option value='" + categorie.getIdCategorie() + "'>" + categorie.getIntitule() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                            <input type="file" name="imageRestaurant"/>
                            <div class="form-group">
                                <input type="submit" name="validerRestaurant" class="login loginmodal-submit btn btn-primary" value="Valider">
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
        <div id="introduction-resto">
            <div class="container">
                <div class="row">
                    <h1>Bienvenue dans la partie restaurateur </h1>
                    <br/>
                    <h3>Devenez membre de la cuillère pour bénéficier de nouveaux clients!</h3>
                    <br/>
                </div>
            </div>
        </div>
        <%
            if(currentSession.getAttribute("utilisateur") != null){
                ArrayList<Categories> categoriesList = (ArrayList<Categories>)currentSession.getAttribute("categoriesList");
                Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
                Collection<Restaurants> restaurantsCollection = (Collection<Restaurants>)utilisateur.getRestaurantsCollection();
        %>
                <div class="liste-restaurants">
                <h2 id="mesRestaurants">Mes Restaurants</h2>
        <%
                for(Restaurants restaurant : restaurantsCollection){
        %>
                    <ul>
                        <li>
                            <div class="image-restaurant"><a target="_blank" href=""><img src=""/></a></div>
                            <div class="information-restaurant">
                                <h3 class="titre-restaurant"><a target="_blank" href=""><% out.print(restaurant.getNom()); %></a></h3>
                                <div class="categorie-restaurant">
                                    <% out.print(restaurant.getFkIdCategorie().getIntitule()); %>
                                </div>
                                <div class="adresse-restaurant">
                                    <% out.print(restaurant.getFkIdAdresse().getNumeroRue() + " " + restaurant.getFkIdAdresse().getNomRue() + " " + restaurant.getFkIdAdresse().getCodePostal()+ " " + restaurant.getFkIdAdresse().getVille()); %>
                                </div>
                            </div>
                            <div class="note-restaurant"></div>
                        </li>
                    </ul>
                </div>
        <%      } 
            }%>
    </body>
</html>
