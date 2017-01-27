/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entity.Categories;
import Entity.Menus;
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
public class MenuBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createMenu(Menus menu) {
        em.persist(menu);
    }

    public void editMenu(Menus menu) {
        em.merge(menu);
    }

    public void removeMenu(Menus menu) {
        em.remove(em.merge(menu));
    }

    public Menus findMenuById(Object id) {
        return em.find(Menus.class, id);
    }
    
    public List <Menus> findAllMenus(){
       return em.createQuery("SELECT m FROM Menus m").getResultList();
    }
    
}
