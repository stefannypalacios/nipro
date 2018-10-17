package sv.com.nipro.interfaz.entities;

public class Agent {

	private String name;
	private String value;
	private String lowerLimit;
	private String upperLimit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(String lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(String upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Override
	public String toString() {
		return "Agent [name=" + name + ", value=" + value + ", lowerLimit=" + lowerLimit + ", upperLimit=" + upperLimit
				+ "]";
	}

}
