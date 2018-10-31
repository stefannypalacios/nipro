/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "element")
@NamedQueries({
    @NamedQuery(name = "Element.findAll", query = "SELECT e FROM Element e"),
    @NamedQuery(name = "Element.findByElementid", query = "SELECT e FROM Element e WHERE e.elementid = :elementid"),
    @NamedQuery(name = "Element.findByElement", query = "SELECT e FROM Element e WHERE e.element = :element"),
    @NamedQuery(name = "Element.findByIdsiap", query = "SELECT e FROM Element e WHERE e.idsiap = :idsiap"),
    @NamedQuery(name = "Element.findBySubelement", query = "SELECT e FROM Element e WHERE e.subelement = :subelement"),
    @NamedQuery(name = "Element.findBySex", query = "SELECT e FROM Element e WHERE e.sex = :sex"),
    @NamedQuery(name = "Element.findByIdedad", query = "SELECT e FROM Element e WHERE e.idedad = :idedad"),
    @NamedQuery(name = "Element.findByAbbreviation", query = "SELECT e FROM Element e WHERE e.abbreviation = :abbreviation")})
public class Element implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "elementid")
    private Integer elementid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "element")
    private String element;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsiap")
    private int idsiap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "subelement")
    private String subelement;
    @Column(name = "sex")
    private Integer sex;
    @Column(name = "idedad")
    private Integer idedad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "abbreviation")
    private String abbreviation;

    public Element() {
    }

    public Element(Integer elementid) {
        this.elementid = elementid;
    }

    public Element(Integer elementid, String element, int idsiap, String subelement, String abbreviation) {
        this.elementid = elementid;
        this.element = element;
        this.idsiap = idsiap;
        this.subelement = subelement;
        this.abbreviation = abbreviation;
    }

    public Integer getElementid() {
        return elementid;
    }

    public void setElementid(Integer elementid) {
        this.elementid = elementid;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getIdsiap() {
        return idsiap;
    }

    public void setIdsiap(int idsiap) {
        this.idsiap = idsiap;
    }

    public String getSubelement() {
        return subelement;
    }

    public void setSubelement(String subelement) {
        this.subelement = subelement;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIdedad() {
        return idedad;
    }

    public void setIdedad(Integer idedad) {
        this.idedad = idedad;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elementid != null ? elementid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Element)) {
            return false;
        }
        Element other = (Element) object;
        if ((this.elementid == null && other.elementid != null) || (this.elementid != null && !this.elementid.equals(other.elementid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Element[ elementid=" + elementid + " ]";
    }
    
}
