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
@Table(name = "menus_annonces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MenusAnnonces.findAll", query = "SELECT m FROM MenusAnnonces m")
    , @NamedQuery(name = "MenusAnnonces.findByIdMenuAnnonce", query = "SELECT m FROM MenusAnnonces m WHERE m.idMenuAnnonce = :idMenuAnnonce")})
public class MenusAnnonces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_menu_annonce")
    private Integer idMenuAnnonce;
    @JoinColumn(name = "fk_id_annonce", referencedColumnName = "id_annonce")
    @ManyToOne
    private Annonces fkIdAnnonce;
    @JoinColumn(name = "fk_id_menu", referencedColumnName = "id_menu")
    @ManyToOne
    private Menus fkIdMenu;

    public MenusAnnonces() {
    }

    public MenusAnnonces(Integer idMenuAnnonce) {
        this.idMenuAnnonce = idMenuAnnonce;
    }

    public Integer getIdMenuAnnonce() {
        return idMenuAnnonce;
    }

    public void setIdMenuAnnonce(Integer idMenuAnnonce) {
        this.idMenuAnnonce = idMenuAnnonce;
    }

    public Annonces getFkIdAnnonce() {
        return fkIdAnnonce;
    }

    public void setFkIdAnnonce(Annonces fkIdAnnonce) {
        this.fkIdAnnonce = fkIdAnnonce;
    }

    public Menus getFkIdMenu() {
        return fkIdMenu;
    }

    public void setFkIdMenu(Menus fkIdMenu) {
        this.fkIdMenu = fkIdMenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMenuAnnonce != null ? idMenuAnnonce.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MenusAnnonces)) {
            return false;
        }
        MenusAnnonces other = (MenusAnnonces) object;
        if ((this.idMenuAnnonce == null && other.idMenuAnnonce != null) || (this.idMenuAnnonce != null && !this.idMenuAnnonce.equals(other.idMenuAnnonce))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.MenusAnnonces[ idMenuAnnonce=" + idMenuAnnonce + " ]";
    }
    
}
