/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.AdresseBean;
import Beans.UtilisateurBean;

import Entity.Adresses;
import Entity.Categories;
import Entity.Restaurants;
import Entity.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anto
 */
public class CreerRestaurant extends HttpServlet {
   @EJB
   private AdresseBean cbean;
   
      @EJB
   private UtilisateurBean bean;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession currentSession = request.getSession();
            String nom = request.getParameter("nom");
            String telephone = request.getParameter("telephone");
            String numeroRue = request.getParameter("numeroRue");
            String nomRue = request.getParameter("nomRue");
            String ville = request.getParameter("ville");
            String codePostal = request.getParameter("codePostal");
            String email = request.getParameter("email");
            
            Integer num= Integer.parseInt(numeroRue);
            Restaurants r = new Restaurants();
            r.setNom(nom);
            r.setEmail(email);
            r.setNumeroTelephone(telephone);
            
            Adresses a= new Adresses();
            a.setCodePostal(codePostal);
            a.setNomRue(nomRue);
            a.setNumeroRue(num);
            a.setVille(ville);
            
            a.ajouterResto(r);
           
            Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
            Utilisateurs u = bean.findUtilisateurById(utilisateur.getIdUtilisateur());
            u.ajouterRestaurant(r);
            
            Categories c = new Categories();
            c.setIntitule("jap");
            c.ajouterResto(r);
            
            cbean.createAdresse(a);
            
            RequestDispatcher rqD = request.getRequestDispatcher("accueil.jsp");
            rqD.forward(request, response);
            out.close();
            
                       
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
