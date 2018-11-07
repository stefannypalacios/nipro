package sv.com.nipro.interfaz.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the archivehl7 database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Archivehl7.findAll", query="SELECT a FROM Archivehl7 a"),
	@NamedQuery(name="Archivehl7.findByArchivehl7id", query="SELECT a FROM Archivehl7 a where a.archivehl7id = :archivehl7id"),
	@NamedQuery(name="Archivehl7.findByResultid", query="SELECT a FROM Archivehl7 a where a.resultid = :resultid"),
	@NamedQuery(name="Archivehl7.findByStatus", query="SELECT a FROM Archivehl7 a where a.status = :status")
})
public class Archivehl7 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer archivehl7id;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String name;

	private String resultid;

	@Column(name="sent_at")
	private Timestamp sentAt;

	private String status;

	public Archivehl7() {
	}

	public Integer getArchivehl7id() {
		return this.archivehl7id;
	}

	public void setArchivehl7id(Integer archivehl7id) {
		this.archivehl7id = archivehl7id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResultid() {
		return this.resultid;
	}

	public void setResultid(String resultid) {
		this.resultid = resultid;
	}

	public Timestamp getSentAt() {
		return this.sentAt;
	}

	public void setSentAt(Timestamp sentAt) {
		this.sentAt = sentAt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
