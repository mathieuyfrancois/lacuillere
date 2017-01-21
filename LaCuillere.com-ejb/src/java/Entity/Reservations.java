/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "reservations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r")
    , @NamedQuery(name = "Reservations.findByIdReservation", query = "SELECT r FROM Reservations r WHERE r.idReservation = :idReservation")
    , @NamedQuery(name = "Reservations.findByNombrePersonnes", query = "SELECT r FROM Reservations r WHERE r.nombrePersonnes = :nombrePersonnes")
    , @NamedQuery(name = "Reservations.findByDate", query = "SELECT r FROM Reservations r WHERE r.date = :date")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reservation")
    private Integer idReservation;
    @Column(name = "nombre_personnes")
    private Integer nombrePersonnes;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @JoinColumn(name = "fk_id_annonce", referencedColumnName = "id_annonce")
    @ManyToOne
    private Annonces fkIdAnnonce;
    @JoinColumn(name = "fk_id_client", referencedColumnName = "id_utilisateur")
    @ManyToOne
    private Utilisateurs fkIdClient;

    public Reservations() {
    }

    public Reservations(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public Integer getNombrePersonnes() {
        return nombrePersonnes;
    }

    public void setNombrePersonnes(Integer nombrePersonnes) {
        this.nombrePersonnes = nombrePersonnes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Annonces getFkIdAnnonce() {
        return fkIdAnnonce;
    }

    public void setFkIdAnnonce(Annonces fkIdAnnonce) {
        this.fkIdAnnonce = fkIdAnnonce;
    }

    public Utilisateurs getFkIdClient() {
        return fkIdClient;
    }

    public void setFkIdClient(Utilisateurs fkIdClient) {
        this.fkIdClient = fkIdClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReservation != null ? idReservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idReservation == null && other.idReservation != null) || (this.idReservation != null && !this.idReservation.equals(other.idReservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Reservations[ idReservation=" + idReservation + " ]";
    }
    
}
