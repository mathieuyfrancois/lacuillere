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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "reductions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reductions.findAll", query = "SELECT r FROM Reductions r")
    , @NamedQuery(name = "Reductions.findByIdReduction", query = "SELECT r FROM Reductions r WHERE r.idReduction = :idReduction")
    , @NamedQuery(name = "Reductions.findByReduction", query = "SELECT r FROM Reductions r WHERE r.reduction = :reduction")})
public class Reductions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_reduction")
    private Integer idReduction;
    @Column(name = "reduction")
    private Integer reduction;
    @OneToMany(mappedBy = "fkIdReduction")
    private Collection<Menus> menusCollection;

    public Reductions() {
    }

    public Reductions(Integer idReduction) {
        this.idReduction = idReduction;
    }

    public Integer getIdReduction() {
        return idReduction;
    }

    public void setIdReduction(Integer idReduction) {
        this.idReduction = idReduction;
    }

    public Integer getReduction() {
        return reduction;
    }

    public void setReduction(Integer reduction) {
        this.reduction = reduction;
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
        hash += (idReduction != null ? idReduction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reductions)) {
            return false;
        }
        Reductions other = (Reductions) object;
        if ((this.idReduction == null && other.idReduction != null) || (this.idReduction != null && !this.idReduction.equals(other.idReduction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Reductions[ idReduction=" + idReduction + " ]";
    }
    
    public void ajouterMenu(Menus menu) {
        this.menusCollection = new ArrayList<>();
        this.menusCollection.add(menu);
        menu.setFkIdReduction(this);
    }
}
