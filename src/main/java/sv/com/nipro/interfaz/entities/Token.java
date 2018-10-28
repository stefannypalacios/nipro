/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Karina
 */
@Entity
@Table(name = "token")
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t"),
    @NamedQuery(name = "Token.findByTokenid", query = "SELECT t FROM Token t WHERE t.tokenid = :tokenid"),
    @NamedQuery(name = "Token.findByToken", query = "SELECT t FROM Token t WHERE t.token = :token"),
    @NamedQuery(name = "Token.findByLastUsage", query = "SELECT t FROM Token t WHERE t.lastUsage = :lastUsage"),
    @NamedQuery(name = "Token.findByStatus", query = "SELECT t FROM Token t WHERE t.status = :status"),
    @NamedQuery(name = "Token.findByCreatedAt", query = "SELECT t FROM Token t WHERE t.createdAt = :createdAt")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tokenid")
    private Integer tokenid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "token")
    private String token;
    @Column(name = "last_usage")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsage;
    @Column(name = "status")
    private Boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tokenid")
    private List<Transaction> transactionList;
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private User userid;

    public Token() {
    }

    public Token(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public Token(Integer tokenid, String token, Date createdAt) {
        this.tokenid = tokenid;
        this.token = token;
        this.createdAt = createdAt;
    }

    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tokenid != null ? tokenid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Token)) {
            return false;
        }
        Token other = (Token) object;
        if ((this.tokenid == null && other.tokenid != null) || (this.tokenid != null && !this.tokenid.equals(other.tokenid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Token[ tokenid=" + tokenid + " ]";
    }
    
}
