/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.MenuBean;
import Beans.PlatBean;
import Beans.ReductionBean;
import Entity.Categories;
import Entity.Menus;
import Entity.Plats;
import Entity.Reductions;
import Entity.Utilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
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
public class AjouterMenu extends HttpServlet{
    @EJB
    private MenuBean menuBean;
    @EJB
    private PlatBean platBean;
    @EJB
    private ReductionBean reductionBean;
     
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession currentSession = request.getSession();
            String nomMenu = request.getParameter("nomMenu");
            Double prix = Double.valueOf(request.getParameter("prix"));
            Integer idReduction = Integer.parseInt(request.getParameter("reduction"));
            Reductions reduction = reductionBean.findReductionById(idReduction);
            
            String nomEntree = request.getParameter("nomEntree");
            String descriptionEntree = request.getParameter("descriptionEntree");
            Plats entree = new Plats();
            entree.setNom(nomEntree);
            entree.setDescription(descriptionEntree);
            
            String nomPlat = request.getParameter("nomPlat");
            String descriptionPlat = request.getParameter("descriptionPlat");
            Plats plat = new Plats();
            entree.setNom(nomPlat);
            entree.setDescription(descriptionPlat);
            
            String nomDessert = request.getParameter("nomDessert");
            String descriptionDessert = request.getParameter("descriptionDessert");
            Plats dessert = new Plats();
            entree.setNom(nomDessert);
            entree.setDescription(descriptionDessert);
            
            Menus menu = new Menus();
            menu.setNom(nomMenu);
            menu.setPrix(prix);
            
            /*menu.ajouterPlat(entree);
            menu.ajouterPlat(plat);
            menu.ajouterPlat(dessert);
            reduction.ajouterMenu(menu);
            
            adresseBean.createAdresse(adresse);*/
        } 
        catch (Exception ex) {
            Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            out.close();
        }
    }
    
}
