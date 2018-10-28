package sv.com.nipro.interfaz.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sample")
public class Sample {
	private String RBC;
	private String MCV;
	private String HCT;
	private String MCH;
	private String MCHC;
	private String RDWR;
	private String RDWA;
	private String PLT;
	private String MPV;
	private String PCT;
	private String PDW;
	private String PDWR;
	private String LPCR;
	private String LPCA;
	private String HGB;
	private String WBC;
	private String LA;
	private String MA;
	private String GA;
	private String LR;
	private String MR;
	private String GR;

	public String getRBC() {
		return RBC;
	}

	@XmlAttribute(name = "RBC")
	public void setRBC(String rBC) {
		RBC = rBC;
	}

	public String getMCV() {
		return MCV;
	}

	@XmlAttribute(name = "MCV")
	public void setMCV(String mCV) {
		MCV = mCV;
	}

	public String getHCT() {
		return HCT;
	}

	@XmlAttribute(name = "HCT")
	public void setHCT(String hCT) {
		HCT = hCT;
	}

	public String getMCH() {
		return MCH;
	}

	@XmlAttribute(name = "MCH")
	public void setMCH(String mCH) {
		MCH = mCH;
	}

	public String getMCHC() {
		return MCHC;
	}

	@XmlAttribute(name = "MCHC")
	public void setMCHC(String mCHC) {
		MCHC = mCHC;
	}

	public String getRDWR() {
		return RDWR;
	}

	@XmlAttribute(name = "RDWR")
	public void setRDWR(String rDWR) {
		RDWR = rDWR;
	}

	public String getRDWA() {
		return RDWA;
	}

	@XmlAttribute(name = "RDWA")
	public void setRDWA(String rDWA) {
		RDWA = rDWA;
	}

	public String getPLT() {
		return PLT;
	}

	@XmlAttribute(name = "PLT")
	public void setPLT(String pLT) {
		PLT = pLT;
	}

	public String getMPV() {
		return MPV;
	}

	@XmlAttribute(name = "MPV")
	public void setMPV(String mPV) {
		MPV = mPV;
	}

	public String getPCT() {
		return PCT;
	}

	@XmlAttribute(name = "PCT")
	public void setPCT(String pCT) {
		PCT = pCT;
	}

	public String getPDW() {
		return PDW;
	}

	@XmlAttribute(name = "PDW")
	public void setPDW(String pDW) {
		PDW = pDW;
	}

	public String getPDWR() {
		return PDWR;
	}

	@XmlAttribute(name = "PDWR")
	public void setPDWR(String pDWR) {
		PDWR = pDWR;
	}

	public String getLPCR() {
		return LPCR;
	}

	@XmlAttribute(name = "LPCR")
	public void setLPCR(String lPCR) {
		LPCR = lPCR;
	}

	public String getLPCA() {
		return LPCA;
	}

	@XmlAttribute(name = "LPCA")
	public void setLPCA(String lPCA) {
		LPCA = lPCA;
	}

	public String getHGB() {
		return HGB;
	}

	@XmlAttribute(name = "HGB")
	public void setHGB(String hGB) {
		HGB = hGB;
	}

	public String getWBC() {
		return WBC;
	}

	@XmlAttribute(name = "WBC")
	public void setWBC(String wBC) {
		WBC = wBC;
	}

	public String getLA() {
		return LA;
	}

	@XmlAttribute(name = "LA")
	public void setLA(String lA) {
		LA = lA;
	}

	public String getMA() {
		return MA;
	}

	@XmlAttribute(name = "MA")
	public void setMA(String mA) {
		MA = mA;
	}

	public String getGA() {
		return GA;
	}

	@XmlAttribute(name = "GA")
	public void setGA(String gA) {
		GA = gA;
	}

	public String getLR() {
		return LR;
	}

	@XmlAttribute(name = "LR")
	public void setLR(String lR) {
		LR = lR;
	}

	public String getMR() {
		return MR;
	}

	@XmlAttribute(name = "MR")
	public void setMR(String mR) {
		MR = mR;
	}

	public String getGR() {
		return GR;
	}

	@XmlAttribute(name = "GR")
	public void setGR(String gR) {
		GR = gR;
	}

	@Override
	public String toString() {
		return "Sample [RBC=" + RBC + ", MCV=" + MCV + ", HCT=" + HCT + ", MCH=" + MCH + ", MCHC=" + MCHC + ", RDWR="
				+ RDWR + ", RDWA=" + RDWA + ", PLT=" + PLT + ", MPV=" + MPV + ", PCT=" + PCT + ", PDW=" + PDW
				+ ", PDWR=" + PDWR + ", LPCR=" + LPCR + ", LPCA=" + LPCA + ", HGB=" + HGB + ", WBC=" + WBC + ", LA="
				+ LA + ", MA=" + MA + ", GA=" + GA + ", LR=" + LR + ", MR=" + MR + ", GR=" + GR + "]";
	}

}