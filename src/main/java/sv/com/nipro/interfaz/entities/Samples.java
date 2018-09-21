package sv.com.nipro.interfaz.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "samples")
public class Samples {
	private Sample sample;

	public Sample getSample() {
		return sample;
	}

	@XmlElement
	public void setSample(Sample sample) {
		this.sample = sample;
	}

	@Override
	public String toString() {
		return "Samples [sample=" + sample + "]";
	}

}
