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
@Table(name = "plats_menus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlatsMenus.findAll", query = "SELECT p FROM PlatsMenus p")
    , @NamedQuery(name = "PlatsMenus.findByIdPlatMenu", query = "SELECT p FROM PlatsMenus p WHERE p.idPlatMenu = :idPlatMenu")})
public class PlatsMenus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_plat_menu")
    private Integer idPlatMenu;
    @JoinColumn(name = "fk_id_menu", referencedColumnName = "id_menu")
    @ManyToOne
    private Menus fkIdMenu;
    @JoinColumn(name = "fk_id_plat", referencedColumnName = "id_plat")
    @ManyToOne
    private Plats fkIdPlat;

    public PlatsMenus() {
    }

    public PlatsMenus(Integer idPlatMenu) {
        this.idPlatMenu = idPlatMenu;
    }

    public Integer getIdPlatMenu() {
        return idPlatMenu;
    }

    public void setIdPlatMenu(Integer idPlatMenu) {
        this.idPlatMenu = idPlatMenu;
    }

    public Menus getFkIdMenu() {
        return fkIdMenu;
    }

    public void setFkIdMenu(Menus fkIdMenu) {
        this.fkIdMenu = fkIdMenu;
    }

    public Plats getFkIdPlat() {
        return fkIdPlat;
    }

    public void setFkIdPlat(Plats fkIdPlat) {
        this.fkIdPlat = fkIdPlat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatMenu != null ? idPlatMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlatsMenus)) {
            return false;
        }
        PlatsMenus other = (PlatsMenus) object;
        if ((this.idPlatMenu == null && other.idPlatMenu != null) || (this.idPlatMenu != null && !this.idPlatMenu.equals(other.idPlatMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.PlatsMenus[ idPlatMenu=" + idPlatMenu + " ]";
    }
    
}
