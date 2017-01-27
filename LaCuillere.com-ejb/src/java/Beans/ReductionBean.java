/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;



import Entity.Reductions;
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
public class ReductionBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createReduction(Reductions reduction) {
        em.persist(reduction);
    }

    public void editReduction(Reductions reduction) {
        em.merge(reduction);
    }

    public void removeReduction(Reductions reduction) {
        em.remove(em.merge(reduction));
    }

    public Reductions findReductionById(Object id) {
        return em.find(Reductions.class, id);
    }
    
    public List <Reductions> findAllReductions(){
       return em.createQuery("SELECT r FROM Reductions r").getResultList();
    }
    
}
