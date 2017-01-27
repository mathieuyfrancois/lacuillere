/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entity.Utilisateurs;
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
public class UtilisateurBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createUtilisateur(Utilisateurs utilisateur) {
        em.persist(utilisateur);
    }

    public void editUtilisateur(Utilisateurs utilisateur) {
        em.merge(utilisateur);
    }

    public void removeUtilisateur(Utilisateurs utilisateur) {
        em.remove(em.merge(utilisateur));
    }

    public Utilisateurs findUtilisateurById(Object id) {
        return em.find(Utilisateurs.class, id);
    }
    
    public Utilisateurs findUtilisateur(Utilisateurs utilisateur) { 
        try{
            String requete = "SELECT u FROM Utilisateurs u WHERE"
                    + " u.email = :uEmail"
                    + " AND u.motDePasse = :uMotDePasse";
            Query q = em.createQuery(requete); 
            q.setParameter("uEmail", utilisateur.getEmail());
            q.setParameter("uMotDePasse", utilisateur.getMotDePasse());
            List<Utilisateurs> res = q.getResultList();
            return res.size() == 0 ? null : res.get(0); 
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public List <Utilisateurs> findAllUtilisateurs(){
       return em.createQuery("SELECT u FROM Utilisateurs u").getResultList();
    }
    
}
