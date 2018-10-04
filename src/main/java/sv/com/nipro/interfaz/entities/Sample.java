package sv.com.nipro.interfaz.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sample")
public class Sample {
	private Smpresults smpresults;

	public Smpresults getSmpresults() {
		return smpresults;
	}

	@XmlElement(name="smpresults")
	public void setSmpresults(Smpresults smpresults) {
		this.smpresults = smpresults;
	}

	@Override
	public String toString() {
		return "Sample [smpresults=" + smpresults + "]";
	}

	

}
