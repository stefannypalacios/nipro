package sv.com.nipro.interfaz.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "smpresults")
public class Smpresults {

	private List<P> p;

	public List<P> getP() {
		return p;
	}

	@XmlElement(name="p")
	public void setP(List<P> p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Smpresults [p=" + p + "]";
	}

}
