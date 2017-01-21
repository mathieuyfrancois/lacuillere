/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pitit
 */
@Entity
@Table(name = "plages_horaires_annonces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlagesHorairesAnnonces.findAll", query = "SELECT p FROM PlagesHorairesAnnonces p")
    , @NamedQuery(name = "PlagesHorairesAnnonces.findByIdPlageHoraireAnnonce", query = "SELECT p FROM PlagesHorairesAnnonces p WHERE p.idPlageHoraireAnnonce = :idPlageHoraireAnnonce")})
public class PlagesHorairesAnnonces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plage_horaire_annonce")
    private Integer idPlageHoraireAnnonce;
    @JoinColumn(name = "fk_id_annonce", referencedColumnName = "id_annonce")
    @ManyToOne
    private Annonces fkIdAnnonce;
    @JoinColumn(name = "fk_id_plage_horaire", referencedColumnName = "id_plage_horaire")
    @ManyToOne
    private PlagesHoraires fkIdPlageHoraire;

    public PlagesHorairesAnnonces() {
    }

    public PlagesHorairesAnnonces(Integer idPlageHoraireAnnonce) {
        this.idPlageHoraireAnnonce = idPlageHoraireAnnonce;
    }

    public Integer getIdPlageHoraireAnnonce() {
        return idPlageHoraireAnnonce;
    }

    public void setIdPlageHoraireAnnonce(Integer idPlageHoraireAnnonce) {
        this.idPlageHoraireAnnonce = idPlageHoraireAnnonce;
    }

    public Annonces getFkIdAnnonce() {
        return fkIdAnnonce;
    }

    public void setFkIdAnnonce(Annonces fkIdAnnonce) {
        this.fkIdAnnonce = fkIdAnnonce;
    }

    public PlagesHoraires getFkIdPlageHoraire() {
        return fkIdPlageHoraire;
    }

    public void setFkIdPlageHoraire(PlagesHoraires fkIdPlageHoraire) {
        this.fkIdPlageHoraire = fkIdPlageHoraire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlageHoraireAnnonce != null ? idPlageHoraireAnnonce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlagesHorairesAnnonces)) {
            return false;
        }
        PlagesHorairesAnnonces other = (PlagesHorairesAnnonces) object;
        if ((this.idPlageHoraireAnnonce == null && other.idPlageHoraireAnnonce != null) || (this.idPlageHoraireAnnonce != null && !this.idPlageHoraireAnnonce.equals(other.idPlageHoraireAnnonce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PlagesHorairesAnnonces[ idPlageHoraireAnnonce=" + idPlageHoraireAnnonce + " ]";
    }
    
}
