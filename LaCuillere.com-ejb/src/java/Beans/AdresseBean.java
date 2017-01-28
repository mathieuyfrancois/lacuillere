/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Adresses;
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
public class AdresseBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createAdresse(Adresses adresse) {
        em.persist(adresse);
    }

    public void editAdresse(Adresses adresse) {
        em.merge(adresse);
    }

    public void removeAdresse(Adresses adresse) {
        em.remove(em.merge(adresse));
    }

    public Adresses findAdresseById(Object id) {
        return em.find(Adresses.class, id);
    }
    
    public Adresses findAdresse(Adresses adresse) { 
        try{
            String requete = "SELECT a FROM Adresses a WHERE"
                    + " a.numeroRue = :aNumeroRue"
                    + " AND a.nomRue = :aNomRue"
                    + " AND a.ville = :aVille"
                    + " AND a.codePostal = :aCodePostal";
            Query q = em.createQuery(requete); 
            q.setParameter("aNumeroRue", adresse.getNumeroRue());
            q.setParameter("aNomRue", adresse.getNomRue());
            q.setParameter("aVille", adresse.getVille());
            q.setParameter("aCodePostal", adresse.getCodePostal());
            List<Adresses> res = q.getResultList();
            return res.size() == 0 ? null : res.get(0); 
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    
    public List <Adresses> findAllAdresses(){
       return em.createQuery("SELECT a FROM Adresses a").getResultList();
    }
    
}
