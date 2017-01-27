/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.PlagesHoraires;
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
public class PlageHoraireBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createPlageHoraire(PlagesHoraires plageHoraire) {
        em.persist(plageHoraire);
    }

    public void editPlageHoraire(PlagesHoraires plageHoraire) {
        em.merge(plageHoraire);
    }

    public void removePlageHoraire(PlagesHoraires plageHoraire) {
        em.remove(em.merge(plageHoraire));
    }

    public PlagesHoraires findPlageHoraireById(Object id) {
        return em.find(PlagesHoraires.class, id);
    }
    
    public List <PlagesHoraires> findAllPlagesHoraires(){
       return em.createQuery("SELECT p FROM PlagesHoraires p").getResultList();
    }
    
}
