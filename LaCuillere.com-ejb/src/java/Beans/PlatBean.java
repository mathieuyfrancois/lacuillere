/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entity.Plats;
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
public class PlatBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createPlat(Plats plat) {
        em.persist(plat);
    }

    public void editPlat(Plats plat) {
        em.merge(plat);
    }

    public void removePlat(Plats plat) {
        em.remove(em.merge(plat));
    }

    public Plats findPlat(Object id) {
        return em.find(Plats.class, id);
    }
    
    public List <Plats> findAllPlats(){
       return em.createQuery("SELECT p FROM Plats p").getResultList();
    }
    
}
