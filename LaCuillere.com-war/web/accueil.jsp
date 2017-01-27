<%-- 
    Document   : accueil
    Created on : 24 janv. 2017, 12:52:01
    Author     : pitit
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
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
    <div class="navbar navbar-default navbar-fixed-top cuillere-navbar" role="navigation">
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
            %>
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown cuillere-dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><%= utilisateur.getPrenom() %>
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="#">Panier</a></li>
                                    <li><a href="#">Mes reservations</a></li>
                                    <li><a href="#">Mes informationz</a></li>
                                    <li><a href="#">Something else here</a></li>
                                    <li role="separator" class="divider"></li>
                                    <li><a href="#">Deconnexion</a></li>
                                <!--    <li><a href="#">Separated link</a></li>
                                    <li><a href="#">One more separated link</a></li>-->
                                </ul>
                            </li>
                        </ul>
                    </div>
            <% } else {%>
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
    
    <!-- Introduction -->
    <div id="introduction">
        <div class="container">
            <div class="row">
                <h1>Bienvenue sur la cuillere</h1>
                <br/>
                <h3>SLOGAN</h3>
                <br/>
                <form class="navbar-form inline-form">
                    <div class="form-group">
                        <input type="search" class="input-sm form-control" placeholder="Recherche">
                        <button type="submit" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-eye-open"></span> Chercher</button>
                    </div>
                </form>
                <br/>
                <div class="col-lg-12">
                </div>
            </div>
        </div>
    </div>
    
    <!-- Compétences -->
    <div id="competences" class="text-center">
        <div class="container">
            <div class="section-title center">
                <h2>Sélection du moment</h2>
                <div class="line">
                    <hr>
                </div>
            </div>
            <div class="space"></div>
            <div class="row">
                <div class="col-md-3 col-sm-6 service">
                    <img src="images/hamburger.jpeg" width="200" height="150"/>
                    <h4>Restaurant 1</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                </div>
                <div class="col-md-3 col-sm-6 service">
                    <img src="images/pates.jpeg" width="200" height="150"/>
                    <h4>Restaurant 2</h4>
                    <p> is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                </div>
                <div class="col-md-3 col-sm-6 service">
                    <img src="images/plat.jpeg" width="200" height="150"/>
                    <h4>Restaurant 3</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                </div>
                <div class="col-md-3 col-sm-6 service">
                    <img src="images/plat_02.jpeg" width="200" height="150"/>
                    <h4>Restaurant 4</h4>
                    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.</p>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>

