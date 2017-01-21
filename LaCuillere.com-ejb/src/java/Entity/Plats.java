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
@Table(name = "plats")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plats.findAll", query = "SELECT p FROM Plats p")
    , @NamedQuery(name = "Plats.findByIdPlat", query = "SELECT p FROM Plats p WHERE p.idPlat = :idPlat")
    , @NamedQuery(name = "Plats.findByNom", query = "SELECT p FROM Plats p WHERE p.nom = :nom")
    , @NamedQuery(name = "Plats.findByDescription", query = "SELECT p FROM Plats p WHERE p.description = :description")})
public class Plats implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plat")
    private Integer idPlat;
    @Size(max = 256)
    @Column(name = "nom")
    private String nom;
    @Size(max = 256)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "fkIdPlat")
    private Collection<PlatsMenus> platsMenusCollection;

    public Plats() {
    }

    public Plats(Integer idPlat) {
        this.idPlat = idPlat;
    }

    public Integer getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Integer idPlat) {
        this.idPlat = idPlat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<PlatsMenus> getPlatsMenusCollection() {
        return platsMenusCollection;
    }

    public void setPlatsMenusCollection(Collection<PlatsMenus> platsMenusCollection) {
        this.platsMenusCollection = platsMenusCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlat != null ? idPlat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plats)) {
            return false;
        }
        Plats other = (Plats) object;
        if ((this.idPlat == null && other.idPlat != null) || (this.idPlat != null && !this.idPlat.equals(other.idPlat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Plats[ idPlat=" + idPlat + " ]";
    }
    
}
