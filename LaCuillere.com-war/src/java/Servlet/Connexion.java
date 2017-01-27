/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.UtilisateurBean;
import Entity.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Connexion extends HttpServlet{
    @EJB
    private UtilisateurBean utilisateurBean;
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession currentSession = request.getSession();
            String email = request.getParameter("email");
            String pass = request.getParameter("pass");
            
            String password = AESencrp.encrypt(pass);
            
            Utilisateurs utilisateur = new Utilisateurs();
            utilisateur.setEmail(email);
            utilisateur.setMotDePasse(password);
            System.out.println(utilisateur);
            
            Utilisateurs utilisateurBDD = new Utilisateurs();
            if(utilisateurBean.findUtilisateur(utilisateur)!= null){
                utilisateurBDD = utilisateurBean.findUtilisateur(utilisateur);
                currentSession.setAttribute("utilisateur", utilisateurBDD);
                RequestDispatcher rqD = request.getRequestDispatcher("accueil.jsp");
                rqD.forward(request, response);
                out.close();
            }
            else{
                //L'utilisateur n'existe pas afficher un message d'erreur
                currentSession.setAttribute("utilisateur", null);
                RequestDispatcher rqD = request.getRequestDispatcher("accueil.jsp");
                rqD.forward(request, response);
                out.close();
            }
            
        } 
        catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }        finally {
            out.close();
        }
    }
}
