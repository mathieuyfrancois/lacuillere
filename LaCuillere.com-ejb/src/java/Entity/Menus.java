/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "menus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menus.findAll", query = "SELECT m FROM Menus m")
    , @NamedQuery(name = "Menus.findByIdMenu", query = "SELECT m FROM Menus m WHERE m.idMenu = :idMenu")
    , @NamedQuery(name = "Menus.findByPrix", query = "SELECT m FROM Menus m WHERE m.prix = :prix")
    , @NamedQuery(name = "Menus.findByNom", query = "SELECT m FROM Menus m WHERE m.nom = :nom")})
public class Menus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu")
    private Integer idMenu;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix")
    private Double prix;
    @Size(max = 256)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "fkIdMenu")
    private Collection<PlatsMenus> platsMenusCollection;
    @JoinColumn(name = "fk_id_reduction", referencedColumnName = "id_reduction")
    @ManyToOne
    private Reductions fkIdReduction;
    @JoinColumn(name = "fk_id_restaurant", referencedColumnName = "id_restaurant")
    @ManyToOne
    private Restaurants fkIdRestaurant;
    @OneToMany(mappedBy = "fkIdMenu")
    private Collection<MenusAnnonces> menusAnnoncesCollection;

    public Menus() {
    }

    public Menus(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<PlatsMenus> getPlatsMenusCollection() {
        return platsMenusCollection;
    }

    public void setPlatsMenusCollection(Collection<PlatsMenus> platsMenusCollection) {
        this.platsMenusCollection = platsMenusCollection;
    }

    public Reductions getFkIdReduction() {
        return fkIdReduction;
    }

    public void setFkIdReduction(Reductions fkIdReduction) {
        this.fkIdReduction = fkIdReduction;
    }

    public Restaurants getFkIdRestaurant() {
        return fkIdRestaurant;
    }

    public void setFkIdRestaurant(Restaurants fkIdRestaurant) {
        this.fkIdRestaurant = fkIdRestaurant;
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
        hash += (idMenu != null ? idMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menus)) {
            return false;
        }
        Menus other = (Menus) object;
        if ((this.idMenu == null && other.idMenu != null) || (this.idMenu != null && !this.idMenu.equals(other.idMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Menus[ idMenu=" + idMenu + " ]";
    }
    
}
