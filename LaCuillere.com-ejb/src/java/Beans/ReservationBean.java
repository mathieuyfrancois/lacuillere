/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;


import Entity.Reservations;
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
public class ReservationBean{
    
    @PersistenceContext
    private EntityManager em;
    
    public void createReservation(Reservations reservation) {
        em.persist(reservation);
    }

    public void editReservation(Reservations reservation) {
        em.merge(reservation);
    }

    public void removeReservation(Reservations reservation) {
        em.remove(em.merge(reservation));
    }

    public Reservations findReservation(Object id) {
        return em.find(Reservations.class, id);
    }
    
    public List <Reservations> findAllReservations(){
       return em.createQuery("SELECT r FROM Reservations r").getResultList();
    }
    
}
