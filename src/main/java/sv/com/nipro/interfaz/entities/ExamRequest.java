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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "exam_request")
@NamedQueries({
    @NamedQuery(name = "ExamRequest.findAll", query = "SELECT e FROM ExamRequest e"),
    @NamedQuery(name = "ExamRequest.findByIdexrequest", query = "SELECT e FROM ExamRequest e WHERE e.idexrequest = :idexrequest"),
    @NamedQuery(name = "ExamRequest.findByIdrequest", query = "SELECT e FROM ExamRequest e WHERE e.idrequest = :idrequest"),
    @NamedQuery(name = "ExamRequest.findByStatus", query = "SELECT e FROM ExamRequest e WHERE e.status = :status"),
    @NamedQuery(name = "ExamRequest.findByReceivedAt", query = "SELECT e FROM ExamRequest e WHERE e.receivedAt = :receivedAt")})
public class ExamRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idexrequest")
    private Integer idexrequest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "idrequest")
    private String idrequest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "received_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedAt;

    public ExamRequest() {
    }

    public ExamRequest(Integer idexrequest) {
        this.idexrequest = idexrequest;
    }

    public ExamRequest(Integer idexrequest, String idrequest, String status, Date receivedAt) {
        this.idexrequest = idexrequest;
        this.idrequest = idrequest;
        this.status = status;
        this.receivedAt = receivedAt;
    }

    public Integer getIdexrequest() {
        return idexrequest;
    }

    public void setIdexrequest(Integer idexrequest) {
        this.idexrequest = idexrequest;
    }

    public String getIdrequest() {
        return idrequest;
    }

    public void setIdrequest(String idrequest) {
        this.idrequest = idrequest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(Date receivedAt) {
        this.receivedAt = receivedAt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idexrequest != null ? idexrequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamRequest)) {
            return false;
        }
        ExamRequest other = (ExamRequest) object;
        if ((this.idexrequest == null && other.idexrequest != null) || (this.idexrequest != null && !this.idexrequest.equals(other.idexrequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExamRequest[ idexrequest=" + idexrequest + " ]";
    }
    
}
