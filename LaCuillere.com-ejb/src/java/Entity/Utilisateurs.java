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
@Table(name = "utilisateurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateurs.findAll", query = "SELECT u FROM Utilisateurs u")
    , @NamedQuery(name = "Utilisateurs.findByNom", query = "SELECT u FROM Utilisateurs u WHERE u.nom = :nom")
    , @NamedQuery(name = "Utilisateurs.findByPrenom", query = "SELECT u FROM Utilisateurs u WHERE u.prenom = :prenom")
    , @NamedQuery(name = "Utilisateurs.findByEmail", query = "SELECT u FROM Utilisateurs u WHERE u.email = :email")
    , @NamedQuery(name = "Utilisateurs.findByMotDePasse", query = "SELECT u FROM Utilisateurs u WHERE u.motDePasse = :motDePasse")
    , @NamedQuery(name = "Utilisateurs.findByNumeroTelephone", query = "SELECT u FROM Utilisateurs u WHERE u.numeroTelephone = :numeroTelephone")
    , @NamedQuery(name = "Utilisateurs.findByIdUtilisateur", query = "SELECT u FROM Utilisateurs u WHERE u.idUtilisateur = :idUtilisateur")
    , @NamedQuery(name = "Utilisateurs.findByEstClient", query = "SELECT u FROM Utilisateurs u WHERE u.estClient = :estClient")})
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 256)
    @Column(name = "nom")
    private String nom;
    @Size(max = 256)
    @Column(name = "prenom")
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 256)
    @Column(name = "email")
    private String email;
    @Size(max = 256)
    @Column(name = "mot_de_passe")
    private String motDePasse;
    @Size(max = 256)
    @Column(name = "numero_telephone")
    private String numeroTelephone;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;
    @Column(name = "est_client")
    private Boolean estClient;
    @JoinColumn(name = "fk_id_adresse", referencedColumnName = "id_adresse")
    @ManyToOne
    private Adresses fkIdAdresse;
    @OneToMany(mappedBy = "fkIdClient")
    private Collection<Reservations> reservationsCollection;
    @OneToMany(mappedBy = "fkIdRestaurateur")
    private Collection<Annonces> annoncesCollection;

    public Utilisateurs() {
    }

    public Utilisateurs(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Boolean getEstClient() {
        return estClient;
    }

    public void setEstClient(Boolean estClient) {
        this.estClient = estClient;
    }

    public Adresses getFkIdAdresse() {
        return fkIdAdresse;
    }

    public void setFkIdAdresse(Adresses fkIdAdresse) {
        this.fkIdAdresse = fkIdAdresse;
    }

    @XmlTransient
    public Collection<Reservations> getReservationsCollection() {
        return reservationsCollection;
    }

    public void setReservationsCollection(Collection<Reservations> reservationsCollection) {
        this.reservationsCollection = reservationsCollection;
    }

    @XmlTransient
    public Collection<Annonces> getAnnoncesCollection() {
        return annoncesCollection;
    }

    public void setAnnoncesCollection(Collection<Annonces> annoncesCollection) {
        this.annoncesCollection = annoncesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUtilisateur != null ? idUtilisateur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurs)) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) object;
        if ((this.idUtilisateur == null && other.idUtilisateur != null) || (this.idUtilisateur != null && !this.idUtilisateur.equals(other.idUtilisateur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Utilisateurs[ idUtilisateur=" + idUtilisateur + " ]";
    }
    
}
