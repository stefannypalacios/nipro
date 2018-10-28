package sv.com.nipro.interfaz.entities;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "samples")
public class Samples {
	private List<Sample> sample;

	public List<Sample> getSample() {
		return sample;	
	}

	@XmlElement(name="sample")
	public void setSample(List<Sample> sample) {
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "Samples [sample=" + sample + "]";
	}
}