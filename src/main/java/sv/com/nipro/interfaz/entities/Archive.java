/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "archive")
@NamedQueries({
    @NamedQuery(name = "Archive.findAll", query = "SELECT a FROM Archive a"),
    @NamedQuery(name = "Archive.findByArchiveid", query = "SELECT a FROM Archive a WHERE a.archiveid = :archiveid"),
    @NamedQuery(name = "Archive.findByType", query = "SELECT a FROM Archive a WHERE a.type = :type"),
    @NamedQuery(name = "Archive.findByName", query = "SELECT a FROM Archive a WHERE a.name = :name"),
    @NamedQuery(name = "Archive.findByCreatedAt", query = "SELECT a FROM Archive a WHERE a.createdAt = :createdAt")})
public class Archive implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "archiveid")
    private Integer archiveid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "transactionid", referencedColumnName = "transactionid")
    @ManyToOne(optional = false)
    private Transaction transactionid;

    public Archive() {
    }

    public Archive(Integer archiveid) {
        this.archiveid = archiveid;
    }

    public Archive(Integer archiveid, String type, String name, Date createdAt) {
        this.archiveid = archiveid;
        this.type = type;
        this.name = name;
        this.createdAt = createdAt;
    }

    public Integer getArchiveid() {
        return archiveid;
    }

    public void setArchiveid(Integer archiveid) {
        this.archiveid = archiveid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Transaction getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(Transaction transactionid) {
        this.transactionid = transactionid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (archiveid != null ? archiveid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archive)) {
            return false;
        }
        Archive other = (Archive) object;
        if ((this.archiveid == null && other.archiveid != null) || (this.archiveid != null && !this.archiveid.equals(other.archiveid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "enterprise.web_jpa_war.entity.Archive[ archiveid=" + archiveid + " ]";
    }
    
}
