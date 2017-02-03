<%-- 
    Document   : informationsUtilisateur
    Created on : 3 févr. 2017, 14:45:54
    Author     : pitit
--%>

<%@page import="utils.AESencrp"%>
<%@page import="Entity.Utilisateurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations Utilisateur</title>
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
                                        <li><a href="informationsUtilisateur.jsp">Mes informations</a></li>
                                        <li role="separator" class="divider"></li>
                                        <li><a href="Deconnexion">Deconnexion</a></li>
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
        
        <%      if(currentSession.getAttribute("utilisateur") != null){
                    Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
        %>
                    <div class="container">
                        <div class="row">
                            <div class="mes-informations">
                                <h1>Mes informations</h1>
                                <form method="post" action="ModificationInformationsUtilisateur" role="form" data-toggle="validator">
                                    <fieldset id="modificationFieldset" class="modal-inputs">
                                        <!-- Input nom -->
                                        <div class="form-group has-feedback">
                                           <div class="input-group">
                                                <input type="text" name="nom" class="form-control" id="inputNomModification" value=<% if(!utilisateur.getNom().isEmpty()){out.print(utilisateur.getNom());} %> required>
                                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                                <div class="help-block with-errors"></div>
                                           </div>
                                        </div>
                                        <!-- Input pour savoir qu'on a à faire à un restaurateur -->
                                        <%
                                            if(utilisateur.getEstClient()){ 
                                        %>
                                                <input type="hidden" name="client" value="oui">
                                        <%  }
                                            else{ 
                                        %>
                                                <input type="hidden" name="client" value="non">
                                        <%  }%>
                                        
                                        <!-- Input prenom -->
                                        <div class="form-group has-feedback">
                                           <div class="input-group">
                                                <input type="text" name="prenom" class="form-control" id="inputPrenomModification" value=<% if(!utilisateur.getPrenom().isEmpty()){out.print(utilisateur.getPrenom());} %> required>
                                                <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                                <div class="help-block with-errors"></div>
                                           </div>
                                        </div>
                                        <!-- Input numero telephone -->
                                        <input type="text" name="telephone" value=<% if(!utilisateur.getNumeroTelephone().isEmpty()){out.print(utilisateur.getNumeroTelephone());} %>>
                                        <fieldset>
                                            <legend>Adresse</legend>
                                            <input type="text" name="numeroRue" value=<% if(utilisateur.getFkIdAdresse() != null){out.print(utilisateur.getFkIdAdresse().getNumeroRue());} %>>
                                            <input type="text" name="nomRue" value=<% if(utilisateur.getFkIdAdresse() != null){out.print(utilisateur.getFkIdAdresse().getNomRue());} %>>
                                            <input type="text" name="ville" value=<% if(utilisateur.getFkIdAdresse() != null){out.print(utilisateur.getFkIdAdresse().getVille());} %>>
                                            <input type="text" name="codePostal" value=<% if(utilisateur.getFkIdAdresse() != null){out.print(utilisateur.getFkIdAdresse().getCodePostal());} %>>
                                        </fieldset>
                                        <fieldset>
                                            <legend>Identifiants</legend>
                                            <div class="form-group has-feedback">
                                               <div class="input-group">
                                                    <input type="email" name="email" class="form-control" id="inputEmailModification" value="<% out.print(utilisateur.getEmail()); %>" data-error="E-mail invalide" required>
                                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                                    <div class="help-block with-errors"></div>
                                               </div>
                                            </div>
                                            <div class="form-group has-feedback">
                                               <div class="input-group">
                                                    <%
                                                        String motDePasse = utilisateur.getMotDePasse();
                                                        String motDePasseEnClair = AESencrp.decrypt(motDePasse);
                                                    %>
                                                    <input type="password" name="pass" class="form-control" id="inputPassModification" value=<% out.print(motDePasseEnClair); %> required>
                                                    <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                                                    <div class="help-block with-errors"></div>
                                               </div>
                                            </div>
                                        </fieldset>
                                        <div class="form-group divButton">
                                            <input type="submit" name="validerModification" class="login loginmodal-submit btn btn-primary buttonModification" value="Valider">
                                        </div>
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
        <%  
            }
        %>
    </body>
</html>
