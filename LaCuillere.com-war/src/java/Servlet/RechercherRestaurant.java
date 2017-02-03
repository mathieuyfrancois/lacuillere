/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Beans.RestaurantBean;
import Entity.Restaurants;
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

/**
 *
 * @author pitit
 */
public class RechercherRestaurant extends HttpServlet{
    @EJB
    private RestaurantBean restaurantBean;
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession currentSession = request.getSession();
            String recherche = request.getParameter("recherche");
           
            ArrayList<Restaurants> listeRestaurants = new ArrayList<Restaurants>();
            if(restaurantBean.findRestaurants(recherche).size() != 0){
                for(Restaurants restaurant : restaurantBean.findRestaurants(recherche)){
                    listeRestaurants.add(restaurant);
                    System.out.println(restaurant.getNom());
                }
                currentSession.setAttribute("listeRestaurants", listeRestaurants);
                System.out.println(restaurantBean.findRestaurants(recherche));
            }           
            
            RequestDispatcher rqD = request.getRequestDispatcher("listeRestaurants.jsp");
            rqD.forward(request, response);
                            
        }
        catch (Exception ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }        
        finally {
            out.close();
        }
    }
}
