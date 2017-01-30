/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "restaurants")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restaurants.findAll", query = "SELECT r FROM Restaurants r")
    , @NamedQuery(name = "Restaurants.findByIdRestaurant", query = "SELECT r FROM Restaurants r WHERE r.idRestaurant = :idRestaurant")
    , @NamedQuery(name = "Restaurants.findByNom", query = "SELECT r FROM Restaurants r WHERE r.nom = :nom")
    , @NamedQuery(name = "Restaurants.findByNote", query = "SELECT r FROM Restaurants r WHERE r.note = :note")
    , @NamedQuery(name = "Restaurants.findByEmail", query = "SELECT r FROM Restaurants r WHERE r.email = :email")
    , @NamedQuery(name = "Restaurants.findByNumeroTelephone", query = "SELECT r FROM Restaurants r WHERE r.numeroTelephone = :numeroTelephone")})
public class Restaurants implements Serializable {

    @JoinColumn(name = "fk_id_restaurateur", referencedColumnName = "id_utilisateur")
    @ManyToOne
    private Utilisateurs fkIdRestaurateur;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_restaurant")
    private Integer idRestaurant;
    @Size(max = 256)
    @Column(name = "nom")
    private String nom;
    @Column(name = "note")
    private Integer note;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "email")
    private String email;
    @Size(max = 256)
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    @OneToMany(mappedBy = "fkIdRestaurant")
    private Collection<Annonces> annoncesCollection;
    @JoinColumn(name = "fk_id_adresse", referencedColumnName = "id_adresse")
    @ManyToOne(cascade=CascadeType.ALL)
    private Adresses fkIdAdresse;
    @JoinColumn(name = "fk_id_categorie", referencedColumnName = "id_categorie")
    @ManyToOne(cascade=CascadeType.ALL)
    private Categories fkIdCategorie;
    @OneToMany(mappedBy = "fkIdRestaurant")
    private Collection<Menus> menusCollection;

    public Restaurants() {
    }

    public Restaurants(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Integer getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Integer idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
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
    public Collection<Annonces> getAnnoncesCollection() {
        return annoncesCollection;
    }

    public void setAnnoncesCollection(Collection<Annonces> annoncesCollection) {
        this.annoncesCollection = annoncesCollection;
    }

    public Adresses getFkIdAdresse() {
        return fkIdAdresse;
    }

    public void setFkIdAdresse(Adresses fkIdAdresse) {
        this.fkIdAdresse = fkIdAdresse;
    }

    public Categories getFkIdCategorie() {
        return fkIdCategorie;
    }

    public void setFkIdCategorie(Categories fkIdCategorie) {
        this.fkIdCategorie = fkIdCategorie;
    }

    @XmlTransient
    public Collection<Menus> getMenusCollection() {
        return menusCollection;
    }

    public void setMenusCollection(Collection<Menus> menusCollection) {
        this.menusCollection = menusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRestaurant != null ? idRestaurant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restaurants)) {
            return false;
        }
        Restaurants other = (Restaurants) object;
        if ((this.idRestaurant == null && other.idRestaurant != null) || (this.idRestaurant != null && !this.idRestaurant.equals(other.idRestaurant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Restaurants[ idRestaurant=" + idRestaurant + " ]";
    }

    public Utilisateurs getFkIdRestaurateur() {
        return fkIdRestaurateur;
    }

    public void setFkIdRestaurateur(Utilisateurs fkIdRestaurateur) {
        this.fkIdRestaurateur = fkIdRestaurateur;
    }
    
}
