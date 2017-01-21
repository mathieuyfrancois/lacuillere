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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "plages_horaires")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlagesHoraires.findAll", query = "SELECT p FROM PlagesHoraires p")
    , @NamedQuery(name = "PlagesHoraires.findByIdPlageHoraire", query = "SELECT p FROM PlagesHoraires p WHERE p.idPlageHoraire = :idPlageHoraire")
    , @NamedQuery(name = "PlagesHoraires.findByPlacesRestantes", query = "SELECT p FROM PlagesHoraires p WHERE p.placesRestantes = :placesRestantes")
    , @NamedQuery(name = "PlagesHoraires.findByPlageHoraire", query = "SELECT p FROM PlagesHoraires p WHERE p.plageHoraire = :plageHoraire")})
public class PlagesHoraires implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plage_horaire")
    private Integer idPlageHoraire;
    @Column(name = "places_restantes")
    private Integer placesRestantes;
    @Column(name = "plage_horaire")
    @Temporal(TemporalType.TIMESTAMP)
    private Date plageHoraire;
    @OneToMany(mappedBy = "fkIdPlageHoraire")
    private Collection<PlagesHorairesAnnonces> plagesHorairesAnnoncesCollection;

    public PlagesHoraires() {
    }

    public PlagesHoraires(Integer idPlageHoraire) {
        this.idPlageHoraire = idPlageHoraire;
    }

    public Integer getIdPlageHoraire() {
        return idPlageHoraire;
    }

    public void setIdPlageHoraire(Integer idPlageHoraire) {
        this.idPlageHoraire = idPlageHoraire;
    }

    public Integer getPlacesRestantes() {
        return placesRestantes;
    }

    public void setPlacesRestantes(Integer placesRestantes) {
        this.placesRestantes = placesRestantes;
    }

    public Date getPlageHoraire() {
        return plageHoraire;
    }

    public void setPlageHoraire(Date plageHoraire) {
        this.plageHoraire = plageHoraire;
    }

    @XmlTransient
    public Collection<PlagesHorairesAnnonces> getPlagesHorairesAnnoncesCollection() {
        return plagesHorairesAnnoncesCollection;
    }

    public void setPlagesHorairesAnnoncesCollection(Collection<PlagesHorairesAnnonces> plagesHorairesAnnoncesCollection) {
        this.plagesHorairesAnnoncesCollection = plagesHorairesAnnoncesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlageHoraire != null ? idPlageHoraire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlagesHoraires)) {
            return false;
        }
        PlagesHoraires other = (PlagesHoraires) object;
        if ((this.idPlageHoraire == null && other.idPlageHoraire != null) || (this.idPlageHoraire != null && !this.idPlageHoraire.equals(other.idPlageHoraire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PlagesHoraires[ idPlageHoraire=" + idPlageHoraire + " ]";
    }
    
}
