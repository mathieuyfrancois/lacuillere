/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "annonces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annonces.findAll", query = "SELECT a FROM Annonces a")
    , @NamedQuery(name = "Annonces.findByIdAnnonce", query = "SELECT a FROM Annonces a WHERE a.idAnnonce = :idAnnonce")
    , @NamedQuery(name = "Annonces.findByDate", query = "SELECT a FROM Annonces a WHERE a.date = :date")
    , @NamedQuery(name = "Annonces.findByEmail", query = "SELECT a FROM Annonces a WHERE a.email = :email")
    , @NamedQuery(name = "Annonces.findByNumeroTelephone", query = "SELECT a FROM Annonces a WHERE a.numeroTelephone = :numeroTelephone")})
public class Annonces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_annonce")
    private Integer idAnnonce;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "email")
    private String email;
    @Size(max = 256)
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    @OneToMany(mappedBy = "fkIdAnnonce")
    private Collection<PlagesHorairesAnnonces> plagesHorairesAnnoncesCollection;
    @OneToMany(mappedBy = "fkIdAnnonce")
    private Collection<Reservations> reservationsCollection;
    @JoinColumn(name = "fk_id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne
    private Restaurants fkIdRestaurant;
    @JoinColumn(name = "fk_id_restaurateur", referencedColumnName = "id_utilisateur")
    @ManyToOne
    private Utilisateurs fkIdRestaurateur;
    @OneToMany(mappedBy = "fkIdAnnonce")
    private Collection<MenusAnnonces> menusAnnoncesCollection;

    public Annonces() {
    }

    public Annonces(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Integer getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(Integer idAnnonce) {
        this.idAnnonce = idAnnonce;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    @XmlTransient
    public Collection<PlagesHorairesAnnonces> getPlagesHorairesAnnoncesCollection() {
        return plagesHorairesAnnoncesCollection;
    }

    public void setPlagesHorairesAnnoncesCollection(Collection<PlagesHorairesAnnonces> plagesHorairesAnnoncesCollection) {
        this.plagesHorairesAnnoncesCollection = plagesHorairesAnnoncesCollection;
    }

    @XmlTransient
    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    public Restaurants getFkIdRestaurant() {
        return fkIdRestaurant;
    }

    public void setFkIdRestaurant(Restaurants fkIdRestaurant) {
        this.fkIdRestaurant = fkIdRestaurant;
    }

    public Utilisateurs getFkIdRestaurateur() {
        return fkIdRestaurateur;
    }

    public void setFkIdRestaurateur(Utilisateurs fkIdRestaurateur) {
        this.fkIdRestaurateur = fkIdRestaurateur;
    }

    @XmlTransient
    public Collection<MenusAnnonces> getMenusAnnoncesCollection() {
        return menusAnnoncesCollection;
    }

    public void setMenusAnnoncesCollection(Collection<MenusAnnonces> menusAnnoncesCollection) {
        this.menusAnnoncesCollection = menusAnnoncesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAnnonce != null ? idAnnonce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annonces)) {
            return false;
        }
        Annonces other = (Annonces) object;
        if ((this.idAnnonce == null && other.idAnnonce != null) || (this.idAnnonce != null && !this.idAnnonce.equals(other.idAnnonce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Annonces[ idAnnonce=" + idAnnonce + " ]";
    }
    
}
