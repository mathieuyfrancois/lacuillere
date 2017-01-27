/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Entity.Annonces;
import Entity.Categories;
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
public class CategorieBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createCategorie(Categories categorie) {
        em.persist(categorie);
    }

    public void editCategorie(Categories categorie) {
        em.merge(categorie);
    }

    public void removeCategorie(Categories categorie) {
        em.remove(em.merge(categorie));
    }

    public Categories findCategorieById(Object id) {
        return em.find(Categories.class, id);
    }
    
    public List <Categories> findAllCategories(){
       return em.createQuery("SELECT c FROM Categories c").getResultList();
    }
    
}
