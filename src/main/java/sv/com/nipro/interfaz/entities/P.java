package sv.com.nipro.interfaz.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="p")
public class P {

	private String n;
	private String v;
	private String l;
	private String h;

	public String getN() {
		return n;
	}

	@XmlElement(name="n")
	public void setN(String n) {
		this.n = n;
	}

	public String getV() {
		return v;
	}

	@XmlElement(name="v")
	public void setV(String v) {
		this.v = v;
	}

	public String getL() {
		return l;
	}

	@XmlElement(name="l")
	public void setL(String l) {
		this.l = l;
	}

	public String getH() {
		return h;
	}

	@XmlElement(name="h")
	public void setH(String h) {
		this.h = h;
	}

	@Override
	public String toString() {
		return "P [n=" + n + ", v=" + v + ", l=" + l + ", h=" + h + "]";
	}

}
