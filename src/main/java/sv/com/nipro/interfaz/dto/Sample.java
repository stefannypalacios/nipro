package sv.com.nipro.interfaz.dto;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sample")
public class Sample {
	private String SEQ;
	private String DATE;
	private String RBC;
	private String RBC_L;
	private String RBC_H;
	private String MCV;
	private String MCV_L;
	private String MCV_H;
	private String HCT;
	private String HCT_L;
	private String HCT_H;
	private String MCH;
	private String MCH_L;
	private String MCH_H;
	private String MCHC;
	private String MCHC_L;
	private String MCHC_H;
	private String RDWR;
	private String RDWR_L;
	private String RDWR_H;
	private String RDWA;
	private String RDWA_L;
	private String RDWA_H;
	private String PLT;
	private String PLT_L;
	private String PLT_H;
	private String MPV;
	private String MPV_L;
	private String MPV_H;
	private String PCT;
	private String PCT_L;
	private String PCT_H;
	private String PDW;
	private String PDW_L;
	private String PDW_H;
	private String PDWR;
	private String PDWR_L;
	private String PDWR_H;
	private String LPCR;
	private String LPCR_L;
	private String LPCR_H;
	private String LPCA;
	private String LPCA_L;
	private String LPCA_H;
	private String HGB;
	private String HGB_L;
	private String HGB_H;
	private String WBC;
	private String WBC_L;
	private String WBC_H;
	private String LA_L;
	private String LA_H;
	private String LA;
	private String MA;
	private String MA_L;
	private String MA_H;
	private String GA;
	private String GA_L;
	private String GA_H;
	private String LR;
	private String LR_L;
	private String LR_H;
	private String MR;
	private String MR_L;
	private String MR_H;
	private String GR;
	private String GR_L;
	private String GR_H;
	
	public String getSEQ() {
		return SEQ;
	}
	
	@XmlAttribute(name = "SEQ")
	public void setSEQ(String sEQ) {
		SEQ = sEQ;
	}
	
	public String getDATE() {
		return DATE;
	}
	
	@XmlAttribute(name = "DATE")
	public void setDATE(String dATE) {
		DATE = dATE;
	}

	public String getRBC() {
		return RBC;
	}

	public String getRBC_L() {
		return RBC_L;
	}

	@XmlAttribute(name = "RBC_L")
	public void setRBC_L(String rBC_L) {
		RBC_L = rBC_L;
	}

	public String getRBC_H() {
		return RBC_H;
	}

	@XmlAttribute(name = "RBC_H")
	public void setRBC_H(String rBC_H) {
		RBC_H = rBC_H;
	}

	public String getMCV() {
		return MCV;
	}

	@XmlAttribute(name = "MCV")
	public void setMCV(String mCV) {
		MCV = mCV;
	}

	public String getMCV_L() {
		return MCV_L;
	}

	@XmlAttribute(name = "MCV_L")
	public void setMCV_L(String mCV_L) {
		MCV_L = mCV_L;
	}

	public String getMCV_H() {
		return MCV_H;
	}

	@XmlAttribute(name = "MCV_H")
	public void setMCV_H(String mCV_H) {
		MCV_H = mCV_H;
	}

	public String getHCT() {
		return HCT;
	}

	@XmlAttribute(name = "HCT")
	public void setHCT(String hCT) {
		HCT = hCT;
	}

	public String getHCT_L() {
		return HCT_L;
	}

	@XmlAttribute(name = "HCT_L")
	public void setHCT_L(String hCT_L) {
		HCT_L = hCT_L;
	}

	public String getHCT_H() {
		return HCT_H;
	}

	@XmlAttribute(name = "HCT_H")
	public void setHCT_H(String hCT_H) {
		HCT_H = hCT_H;
	}

	public String getMCH() {
		return MCH;
	}

	@XmlAttribute(name = "MCH")
	public void setMCH(String mCH) {
		MCH = mCH;
	}

	public String getMCH_L() {
		return MCH_L;
	}

	@XmlAttribute(name = "MCH_L")
	public void setMCH_L(String mCH_L) {
		MCH_L = mCH_L;
	}

	public String getMCH_H() {
		return MCH_H;
	}

	@XmlAttribute(name = "MCH_H")
	public void setMCH_H(String mCH_H) {
		MCH_H = mCH_H;
	}

	public String getMCHC() {
		return MCHC;
	}

	@XmlAttribute(name = "MCHC")
	public void setMCHC(String mCHC) {
		MCHC = mCHC;
	}

	public String getMCHC_L() {
		return MCHC_L;
	}

	@XmlAttribute(name = "MCHC_L")
	public void setMCHC_L(String mCHC_L) {
		MCHC_L = mCHC_L;
	}

	public String getMCHC_H() {
		return MCHC_H;
	}

	@XmlAttribute(name = "MCHC_H")
	public void setMCHC_H(String mCHC_H) {
		MCHC_H = mCHC_H;
	}

	public String getRDWR() {
		return RDWR;
	}

	@XmlAttribute(name = "RDWR")
	public void setRDWR(String rDWR) {
		RDWR = rDWR;
	}

	public String getRDWR_L() {
		return RDWR_L;
	}

	@XmlAttribute(name = "RDWR_L")
	public void setRDWR_L(String rDWR_L) {
		RDWR_L = rDWR_L;
	}

	public String getRDWR_H() {
		return RDWR_H;
	}

	@XmlAttribute(name = "RDWR_H")
	public void setRDWR_H(String rDWR_H) {
		RDWR_H = rDWR_H;
	}

	public String getRDWA() {
		return RDWA;
	}

	@XmlAttribute(name = "RDWA")
	public void setRDWA(String rDWA) {
		RDWA = rDWA;
	}

	public String getRDWA_L() {
		return RDWA_L;
	}

	@XmlAttribute(name = "RDWA_L")
	public void setRDWA_L(String rDWA_L) {
		RDWA_L = rDWA_L;
	}

	public String getRDWA_H() {
		return RDWA_H;
	}

	@XmlAttribute(name = "RDWA_H")
	public void setRDWA_H(String rDWA_H) {
		RDWA_H = rDWA_H;
	}

	public String getPLT() {
		return PLT;
	}

	@XmlAttribute(name = "PLT")
	public void setPLT(String pLT) {
		PLT = pLT;
	}

	public String getPLT_L() {
		return PLT_L;
	}

	@XmlAttribute(name = "PLT_L")
	public void setPLT_L(String pLT_L) {
		PLT_L = pLT_L;
	}

	public String getPLT_H() {
		return PLT_H;
	}

	@XmlAttribute(name = "PLT_H")
	public void setPLT_H(String pLT_H) {
		PLT_H = pLT_H;
	}

	public String getMPV() {
		return MPV;
	}

	@XmlAttribute(name = "MPV")
	public void setMPV(String mPV) {
		MPV = mPV;
	}

	public String getMPV_L() {
		return MPV_L;
	}

	@XmlAttribute(name = "MPV_L")
	public void setMPV_L(String mPV_L) {
		MPV_L = mPV_L;
	}

	public String getMPV_H() {
		return MPV_H;
	}

	@XmlAttribute(name = "MPV_H")
	public void setMPV_H(String mPV_H) {
		MPV_H = mPV_H;
	}

	public String getPCT() {
		return PCT;
	}

	@XmlAttribute(name = "PCT")
	public void setPCT(String pCT) {
		PCT = pCT;
	}

	public String getPCT_L() {
		return PCT_L;
	}

	@XmlAttribute(name = "PCT_L")
	public void setPCT_L(String pCT_L) {
		PCT_L = pCT_L;
	}

	public String getPCT_H() {
		return PCT_H;
	}

	@XmlAttribute(name = "PCT_H")
	public void setPCT_H(String pCT_H) {
		PCT_H = pCT_H;
	}

	public String getPDW() {
		return PDW;
	}

	@XmlAttribute(name = "PDW")
	public void setPDW(String pDW) {
		PDW = pDW;
	}

	public String getPDW_L() {
		return PDW_L;
	}

	@XmlAttribute(name = "PDW_L")
	public void setPDW_L(String pDW_L) {
		PDW_L = pDW_L;
	}

	public String getPDW_H() {
		return PDW_H;
	}

	@XmlAttribute(name = "PDW_H")
	public void setPDW_H(String pDW_H) {
		PDW_H = pDW_H;
	}

	public String getPDWR() {
		return PDWR;
	}

	@XmlAttribute(name = "PDWR")
	public void setPDWR(String pDWR) {
		PDWR = pDWR;
	}

	public String getPDWR_L() {
		return PDWR_L;
	}

	@XmlAttribute(name = "PDWR_L")
	public void setPDWR_L(String pDWR_L) {
		PDWR_L = pDWR_L;
	}

	public String getPDWR_H() {
		return PDWR_H;
	}

	@XmlAttribute(name = "PDWR_H")
	public void setPDWR_H(String pDWR_H) {
		PDWR_H = pDWR_H;
	}

	public String getLPCR() {
		return LPCR;
	}

	@XmlAttribute(name = "LPCR")
	public void setLPCR(String lPCR) {
		LPCR = lPCR;
	}

	public String getLPCR_L() {
		return LPCR_L;
	}

	@XmlAttribute(name = "LPCR_L")
	public void setLPCR_L(String lPCR_L) {
		LPCR_L = lPCR_L;
	}

	public String getLPCR_H() {
		return LPCR_H;
	}

	@XmlAttribute(name = "LPCR_H")
	public void setLPCR_H(String lPCR_H) {
		LPCR_H = lPCR_H;
	}

	public String getLPCA() {
		return LPCA;
	}

	@XmlAttribute(name = "LPCA")
	public void setLPCA(String lPCA) {
		LPCA = lPCA;
	}

	public String getLPCA_L() {
		return LPCA_L;
	}

	@XmlAttribute(name = "LPCA_L")
	public void setLPCA_L(String lPCA_L) {
		LPCA_L = lPCA_L;
	}

	public String getLPCA_H() {
		return LPCA_H;
	}

	@XmlAttribute(name = "LPCA_H")
	public void setLPCA_H(String lPCA_H) {
		LPCA_H = lPCA_H;
	}

	public String getHGB() {
		return HGB;
	}

	@XmlAttribute(name = "HGB")
	public void setHGB(String hGB) {
		HGB = hGB;
	}

	public String getHGB_L() {
		return HGB_L;
	}

	@XmlAttribute(name = "HGB_L")
	public void setHGB_L(String hGB_L) {
		HGB_L = hGB_L;
	}

	public String getHGB_H() {
		return HGB_H;
	}

	@XmlAttribute(name = "HGB_H")
	public void setHGB_H(String hGB_H) {
		HGB_H = hGB_H;
	}

	public String getWBC() {
		return WBC;
	}

	@XmlAttribute(name = "WBC")
	public void setWBC(String wBC) {
		WBC = wBC;
	}

	public String getWBC_L() {
		return WBC_L;
	}

	@XmlAttribute(name = "WBC_L")
	public void setWBC_L(String wBC_L) {
		WBC_L = wBC_L;
	}

	public String getWBC_H() {
		return WBC_H;
	}

	@XmlAttribute(name = "WBC_H")
	public void setWBC_H(String wBC_H) {
		WBC_H = wBC_H;
	}

	public String getLA_L() {
		return LA_L;
	}

	@XmlAttribute(name = "LA_L")
	public void setLA_L(String lA_L) {
		LA_L = lA_L;
	}

	public String getLA_H() {
		return LA_H;
	}

	@XmlAttribute(name = "LA_H")
	public void setLA_H(String lA_H) {
		LA_H = lA_H;
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

	public String getMA_L() {
		return MA_L;
	}

	@XmlAttribute(name = "MA_L")
	public void setMA_L(String mA_L) {
		MA_L = mA_L;
	}

	public String getMA_H() {
		return MA_H;
	}

	@XmlAttribute(name = "MA_H")
	public void setMA_H(String mA_H) {
		MA_H = mA_H;
	}

	public String getGA() {
		return GA;
	}

	@XmlAttribute(name = "GA")
	public void setGA(String gA) {
		GA = gA;
	}

	public String getGA_L() {
		return GA_L;
	}

	@XmlAttribute(name = "GA_L")
	public void setGA_L(String gA_L) {
		GA_L = gA_L;
	}

	public String getGA_H() {
		return GA_H;
	}

	@XmlAttribute(name = "GA_H")
	public void setGA_H(String gA_H) {
		GA_H = gA_H;
	}

	public String getLR() {
		return LR;
	}

	@XmlAttribute(name = "LR")
	public void setLR(String lR) {
		LR = lR;
	}

	public String getLR_L() {
		return LR_L;
	}

	@XmlAttribute(name = "LR_L")
	public void setLR_L(String lR_L) {
		LR_L = lR_L;
	}

	public String getLR_H() {
		return LR_H;
	}

	@XmlAttribute(name = "LR_H")
	public void setLR_H(String lR_H) {
		LR_H = lR_H;
	}

	public String getMR() {
		return MR;
	}

	@XmlAttribute(name = "MR")
	public void setMR(String mR) {
		MR = mR;
	}

	public String getMR_L() {
		return MR_L;
	}

	@XmlAttribute(name = "MR_L")
	public void setMR_L(String mR_L) {
		MR_L = mR_L;
	}

	public String getMR_H() {
		return MR_H;
	}

	@XmlAttribute(name = "MR_H")
	public void setMR_H(String mR_H) {
		MR_H = mR_H;
	}

	public String getGR() {
		return GR;
	}

	@XmlAttribute(name = "GR")
	public void setGR(String gR) {
		GR = gR;
	}

	public String getGR_L() {
		return GR_L;
	}

	@XmlAttribute(name = "GR_L")
	public void setGR_L(String gR_L) {
		GR_L = gR_L;
	}

	public String getGR_H() {
		return GR_H;
	}

	@XmlAttribute(name = "GR_H")
	public void setGR_H(String gR_H) {
		GR_H = gR_H;
	}

	@XmlAttribute(name = "RBC")
	public void setRBC(String rBC) {
		RBC = rBC;
	}

	@Override
	public String toString() {
		return "Sample [RBC=" + RBC + ", RBC_L=" + RBC_L + ", RBC_H=" + RBC_H + ", MCV=" + MCV + ", MCV_L=" + MCV_L
				+ ", MCV_H=" + MCV_H + ", HCT=" + HCT + ", HCT_L=" + HCT_L + ", HCT_H=" + HCT_H + ", MCH=" + MCH
				+ ", MCH_L=" + MCH_L + ", MCH_H=" + MCH_H + ", MCHC=" + MCHC + ", MCHC_L=" + MCHC_L + ", MCHC_H="
				+ MCHC_H + ", RDWR=" + RDWR + ", RDWR_L=" + RDWR_L + ", RDWR_H=" + RDWR_H + ", RDWA=" + RDWA
				+ ", RDWA_L=" + RDWA_L + ", RDWA_H=" + RDWA_H + ", PLT=" + PLT + ", PLT_L=" + PLT_L + ", PLT_H=" + PLT_H
				+ ", MPV=" + MPV + ", MPV_L=" + MPV_L + ", MPV_H=" + MPV_H + ", PCT=" + PCT + ", PCT_L=" + PCT_L
				+ ", PCT_H=" + PCT_H + ", PDW=" + PDW + ", PDW_L=" + PDW_L + ", PDW_H=" + PDW_H + ", PDWR=" + PDWR
				+ ", PDWR_L=" + PDWR_L + ", PDWR_H=" + PDWR_H + ", LPCR=" + LPCR + ", LPCR_L=" + LPCR_L + ", LPCR_H="
				+ LPCR_H + ", LPCA=" + LPCA + ", LPCA_L=" + LPCA_L + ", LPCA_H=" + LPCA_H + ", HGB=" + HGB + ", HGB_L="
				+ HGB_L + ", HGB_H=" + HGB_H + ", WBC=" + WBC + ", WBC_L=" + WBC_L + ", WBC_H=" + WBC_H + ", LA_L="
				+ LA_L + ", LA_H=" + LA_H + ", LA=" + LA + ", MA=" + MA + ", MA_L=" + MA_L + ", MA_H=" + MA_H + ", GA="
				+ GA + ", GA_L=" + GA_L + ", GA_H=" + GA_H + ", LR=" + LR + ", LR_L=" + LR_L + ", LR_H=" + LR_H
				+ ", MR=" + MR + ", MR_L=" + MR_L + ", MR_H=" + MR_H + ", GR=" + GR + ", GR_L=" + GR_L + ", GR_H="
				+ GR_H + "]";
	}

	

}