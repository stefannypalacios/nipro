package sv.com.nipro.interfaz.dto;

import java.util.List;

public class Hl7DTO {
	
	private String MSH;
	private String ORC;
	private String OBR;
	private List<String> OBXLlst;
	public String getMSH() {
		return MSH;
	}
	public void setMSH(String mSH) {
		MSH = mSH;
	}
	public String getORC() {
		return ORC;
	}
	public void setORC(String oRC) {
		ORC = oRC;
	}
	public String getOBR() {
		return OBR;
	}
	public void setOBR(String oBR) {
		OBR = oBR;
	}
	public List<String> getOBXLlst() {
		return OBXLlst;
	}
	public void setOBXLlst(List<String> oBXLlst) {
		OBXLlst = oBXLlst;
	}
	public String getHL7(){
		String HL7 = "";
		
		HL7 += MSH + ORC + OBR;
		if (OBXLlst != null){
			for (String obx : OBXLlst) {
				HL7 += obx;
			}
		}
		
		return HL7;
	}
	
	

}
