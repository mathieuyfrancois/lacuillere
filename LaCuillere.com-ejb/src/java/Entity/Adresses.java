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
import javax.persistence.CascadeType;
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
@Table(name = "adresses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresses.findAll", query = "SELECT a FROM Adresses a")
    , @NamedQuery(name = "Adresses.findByIdAdresse", query = "SELECT a FROM Adresses a WHERE a.idAdresse = :idAdresse")
    , @NamedQuery(name = "Adresses.findByNumeroRue", query = "SELECT a FROM Adresses a WHERE a.numeroRue = :numeroRue")
    , @NamedQuery(name = "Adresses.findByNomRue", query = "SELECT a FROM Adresses a WHERE a.nomRue = :nomRue")
    , @NamedQuery(name = "Adresses.findByVille", query = "SELECT a FROM Adresses a WHERE a.ville = :ville")
    , @NamedQuery(name = "Adresses.findByCodePostal", query = "SELECT a FROM Adresses a WHERE a.codePostal = :codePostal")})
public class Adresses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adresse")
    private Integer idAdresse;
    @Column(name = "numero_rue")
    private Integer numeroRue;
    @Size(max = 256)
    @Column(name = "nom_rue")
    private String nomRue;
    @Size(max = 256)
    @Column(name = "ville")
    private String ville;
    @Size(max = 256)
    @Column(name = "code_postal")
    private String codePostal;
    @OneToMany(mappedBy = "fkIdAdresse", cascade = CascadeType.ALL)
    private Collection<Utilisateurs> utilisateursCollection;
    @OneToMany(mappedBy = "fkIdAdresse", cascade = CascadeType.ALL)
    private Collection<Restaurants> restaurantsCollection;

    public Adresses() {
    }

    public Adresses(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Integer getIdAdresse() {
        return idAdresse;
    }

    public void setIdAdresse(Integer idAdresse) {
        this.idAdresse = idAdresse;
    }

    public Integer getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(Integer numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    @XmlTransient
    public Collection<Utilisateurs> getUtilisateursCollection() {
        return utilisateursCollection;
    }

    public void setUtilisateursCollection(Collection<Utilisateurs> utilisateursCollection) {
        this.utilisateursCollection = utilisateursCollection;
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
        hash += (idAdresse != null ? idAdresse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresses)) {
            return false;
        }
        Adresses other = (Adresses) object;
        if ((this.idAdresse == null && other.idAdresse != null) || (this.idAdresse != null && !this.idAdresse.equals(other.idAdresse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Adresses[ idAdresse=" + idAdresse + " ]";
    }
 
    public void ajouterUtilisateur(Utilisateurs user) {
        this.utilisateursCollection = new ArrayList<>();
        this.utilisateursCollection.add(user);
        user.setFkIdAdresse(this);
    }
    
     public void ajouterResto(Restaurants r) {
        this.restaurantsCollection = new ArrayList<>();
        this.restaurantsCollection.add(r);
        r.setFkIdAdresse(this);
    }
}
