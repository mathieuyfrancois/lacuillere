/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.AdresseBean;
import Beans.CategorieBean;
import Beans.UtilisateurBean;

import Entity.Adresses;
import Entity.Categories;
import Entity.Restaurants;
import Entity.Utilisateurs;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author anto
 */
@MultipartConfig
@WebServlet("/CreerRestaurant")
public class CreerRestaurant extends HttpServlet {
    @EJB
    private AdresseBean adresseBean;
   
    @EJB
    private UtilisateurBean utilisateurBean;
    
    @EJB
    private CategorieBean categorieBean;

    private String filePath = "C:\\Users\\pitit\\Documents\\NetBeansProjects\\LaCuillere.com\\LaCuillere.com-war\\web\\images\\restaurants\\";
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            Integer idCategorie = Integer.parseInt(request.getParameter("categorie"));
            Categories categorie = categorieBean.findCategorieById(idCategorie);
            
            File uploads = new File(filePath);
            Part filePart = request.getPart("imageRestaurant");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, uploads.toPath());
            
            Integer num= Integer.parseInt(numeroRue);
            Restaurants restaurant = new Restaurants();
            restaurant.setNom(nom);
            restaurant.setEmail(email);
            restaurant.setNumeroTelephone(telephone);
            
            Adresses adresse= new Adresses();
            adresse.setCodePostal(codePostal);
            adresse.setNomRue(nomRue);
            adresse.setNumeroRue(num);
            adresse.setVille(ville);
            
            adresse.ajouterResto(restaurant);
           
            Utilisateurs utilisateur = (Utilisateurs)currentSession.getAttribute("utilisateur");
            //Utilisateurs u = bean.findUtilisateurById(utilisateur.getIdUtilisateur());
            utilisateur.ajouterRestaurant(restaurant);
            
            categorie.ajouterResto(restaurant);
            
            adresseBean.createAdresse(adresse);
            
            RequestDispatcher rqD = request.getRequestDispatcher("accueilRestaurateur.jsp");
            rqD.forward(request, response);
            out.close();
        }
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
