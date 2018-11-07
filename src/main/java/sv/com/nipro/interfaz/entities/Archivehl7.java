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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "archivehl7")
@NamedQueries({
    @NamedQuery(name = "Archivehl7.findAll", query = "SELECT a FROM Archivehl7 a"),
    @NamedQuery(name = "Archivehl7.findByArchivehl7id", query = "SELECT a FROM Archivehl7 a WHERE a.archivehl7id = :archivehl7id"),
    @NamedQuery(name = "Archivehl7.findByResultid", query = "SELECT a FROM Archivehl7 a WHERE a.resultid = :resultid"),
    @NamedQuery(name = "Archivehl7.findByName", query = "SELECT a FROM Archivehl7 a WHERE a.name = :name"),
    @NamedQuery(name = "Archivehl7.findByStatus", query = "SELECT a FROM Archivehl7 a WHERE a.status = :status"),
    @NamedQuery(name = "Archivehl7.findByCreatedAt", query = "SELECT a FROM Archivehl7 a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "Archivehl7.findBySentAt", query = "SELECT a FROM Archivehl7 a WHERE a.sentAt = :sentAt")})
public class Archivehl7 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "archivehl7id")
    private Integer archivehl7id;
    @Size(max = 50)
    @Column(name = "resultid")
    private String resultid;
    @Size(max = 200)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "sent_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentAt;

    public Archivehl7() {
    }

    public Archivehl7(Integer archivehl7id) {
        this.archivehl7id = archivehl7id;
    }

    public Integer getArchivehl7id() {
        return archivehl7id;
    }

    public void setArchivehl7id(Integer archivehl7id) {
        this.archivehl7id = archivehl7id;
    }

    public String getResultid() {
        return resultid;
    }

    public void setResultid(String resultid) {
        this.resultid = resultid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (archivehl7id != null ? archivehl7id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivehl7)) {
            return false;
        }
        Archivehl7 other = (Archivehl7) object;
        if ((this.archivehl7id == null && other.archivehl7id != null) || (this.archivehl7id != null && !this.archivehl7id.equals(other.archivehl7id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Archivehl7[ archivehl7id=" + archivehl7id + " ]";
    }
    
}
