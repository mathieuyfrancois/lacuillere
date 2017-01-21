/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Annonces;
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
public class AnnonceBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createAnnonce(Annonces annonce) {
        em.persist(annonce);
    }

    public void editAnnonce(Annonces annonce) {
        em.merge(annonce);
    }

    public void removeAnnonce(Annonces annonce) {
        em.remove(em.merge(annonce));
    }

    public Annonces findAnnonce(Object id) {
        return em.find(Annonces.class, id);
    }
    
    public List <Annonces> findAllAnnonces(){
       return em.createQuery("SELECT a FROM Annonces a").getResultList();
    }
    
}
