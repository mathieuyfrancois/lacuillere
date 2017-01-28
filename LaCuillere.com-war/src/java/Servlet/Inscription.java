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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AESencrp;

/**
 *
 * @author pitit
 */
public class Inscription extends HttpServlet{
    @EJB
    private AdresseBean adresseBean;
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession currentSession = request.getSession();
            String nom = request.getParameter("nom");
            String prenom = request.getParameter("prenom");
            String telephone = request.getParameter("telephone");
            String numeroRue = request.getParameter("numeroRue");
            String nomRue = request.getParameter("nomRue");
            String ville = request.getParameter("ville");
            String codePostal = request.getParameter("codePostal");
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setNom(nom.toUpperCase());
            utilisateur.setPrenom(prenom.toUpperCase());
            utilisateur.setNumeroTelephone(telephone);
            utilisateur.setEmail(email);
                        
            try {
                String password;
                password = AESencrp.encrypt(pass);
                utilisateur.setMotDePasse(password);
            } catch (Exception ex) {
                Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Create Adresse
            Adresses adresse = new Adresses();
            adresse.setNumeroRue(Integer.parseInt(numeroRue));
            adresse.setNomRue(nomRue);
            adresse.setVille(ville);
            adresse.setCodePostal(codePostal);
            
            adresse.ajouterUtilisateur(utilisateur);
            adresseBean.createAdresse(adresse);
                                
            //on ajoute l'utilisateur dans la session courante
            currentSession.setAttribute("utilisateur", utilisateur);
            
            RequestDispatcher rqD = request.getRequestDispatcher("accueil.jsp");
            rqD.forward(request, response);
            out.close();
        } 
        catch (Exception ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {
            out.close();
        }
    }
}
