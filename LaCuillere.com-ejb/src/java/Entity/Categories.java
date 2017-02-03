/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categories.findAll", query = "SELECT c FROM Categories c")
    , @NamedQuery(name = "Categories.findByIdCategorie", query = "SELECT c FROM Categories c WHERE c.idCategorie = :idCategorie")
    , @NamedQuery(name = "Categories.findByIntitule", query = "SELECT c FROM Categories c WHERE c.intitule = :intitule")})
public class Categories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categorie")
    private Integer idCategorie;
    @Size(max = 256)
    @Column(name = "intitule")
    private String intitule;
    @OneToMany(mappedBy = "fkIdCategorie")
    private Collection<Restaurants> restaurantsCollection;

    public Categories() {
    }

    public Categories(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @XmlTransient
    public Collection<Restaurants> getRestaurantsCollection() {
        return restaurantsCollection;
    }

    public void setRestaurantsCollection(Collection<Restaurants> restaurantsCollection) {
        this.restaurantsCollection = restaurantsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategorie != null ? idCategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categories)) {
            return false;
        }
        Categories other = (Categories) object;
        if ((this.idCategorie == null && other.idCategorie != null) || (this.idCategorie != null && !this.idCategorie.equals(other.idCategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Categories[ idCategorie=" + idCategorie + " ]";
    }
    
    public void ajouterResto(Restaurants r) {
        this.restaurantsCollection = new ArrayList<>();
        this.restaurantsCollection.add(r);
        r.setFkIdCategorie(this);
    }
}
