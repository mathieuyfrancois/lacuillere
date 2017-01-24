/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.AdresseBean;
import Beans.UtilisateurBean;
import Entity.Adresses;
import Entity.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pitit
 */
public class Inscription extends HttpServlet{
    @EJB
    private UtilisateurBean utilisateurBean;
    private AdresseBean adresseBean;
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession currentSession = request.getSession();
            /*String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone = request.getParameter("telephone");
            String numeroRue = request.getParameter("numeroRue");
            String nomRue = request.getParameter("nomRue");
            String ville = request.getParameter("ville");
            String codePostal = request.getParameter("codePostal");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setNom(nom);
            utilisateur.setPrenom(prenom);
            utilisateur.setNumeroTelephone(telephone);
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(pass);*/
            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setNom("Mathieu");
            utilisateur.setPrenom("dd");
            utilisateur.setNumeroTelephone("0534");
            utilisateur.setEmail("ktle@gmail.com");
            utilisateur.setMotDePasse("blabla");
            /*
            //Create Adresse
            Adresses adresse = new Adresses();
            adresse.setNumeroRue(Integer.parseInt(numeroRue));
            adresse.setNomRue(nomRue);
            adresse.setVille(ville);
            adresse.setCodePostal(codePostal);
            //on ajoute l'adresse dans la base de données
            adresseBean.createAdresse(adresse);
            //List adressesList = adresseBean.findAllAdresses();
            //trouver l'id de l'adresse pour l'ajouter à l'utilisateur
            /*Adresse 
            int fkIdAdresse = 
            utilisateur.setFkIdAdresse(fkIdAdresse);*/
            
            //on ajoute l'utilisateur dans la base de données
            utilisateurBean.createUtilisateur(utilisateur);
            //on ajoute l'utilisateur dans la session courante
            currentSession.setAttribute("utilisateur", utilisateur);
            
            RequestDispatcher rqD = request.getRequestDispatcher("accueil.jsp");
            rqD.forward(request, response);
            out.close();
        } 
        finally {
            out.close();
        }
    }
}
