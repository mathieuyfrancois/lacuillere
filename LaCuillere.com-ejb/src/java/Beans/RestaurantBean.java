/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entity.Restaurants;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author pitit
 */
@Stateless
@LocalBean
public class RestaurantBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createRestaurant(Restaurants restaurant) {
        em.persist(restaurant);
    }

    public void editRestaurant(Restaurants restaurant) {
        em.merge(restaurant);
    }

    public void removeRestaurant(Restaurants restaurant) {
        em.remove(em.merge(restaurant));
    }

    public Restaurants findRestaurant(Object id) {
        return em.find(Restaurants.class, id);
    }
    
    public List <Restaurants> findAllRestaurants(){
       return em.createQuery("SELECT r FROM Restaurants r").getResultList();
    }
    
}
