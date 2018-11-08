package sv.com.nipro.interfaz.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parameter database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Parameter.findAll", query="SELECT p FROM Parameter p"),
	@NamedQuery(name="Parameter.findByCode", query="SELECT p FROM Parameter p WHERE p.code = :code")
})
public class Parameter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer parameterid;

	private String code;

	private String value;

	public Parameter() {
	}

	public Integer getParameterid() {
		return this.parameterid;
	}

	public void setParameterid(Integer parameterid) {
		this.parameterid = parameterid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}