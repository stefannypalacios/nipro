package sv.com.nipro.interfaz.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sv.com.nipro.interfaz.dto.Hl7DTO;
import sv.com.nipro.interfaz.dto.Sample;
import sv.com.nipro.interfaz.dto.Samples;
import sv.com.nipro.interfaz.entities.Element;


public class XMLProcessor {
	public Samples XMLToObject(String path) {
		Samples object = null;
		try {
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Samples.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (Samples) jaxbUnmarshaller.unmarshal(file);
			//System.out.println(object);
			
			
			Hl7DTO hl7 = new Hl7DTO();
			
			//Llenar lista con todos los elementos
			List<Element> lstEl = new ArrayList<Element>();
			
			hl7.getOBXLlst().add(Constans.OBX_1);
			
			for (Sample s : object.getSample()) {
				String OBX = Constans.OBX;
				int i = 2;
				
				for (Element element : lstEl) {
					OBX = OBX.replace("{AUTOINCREMENTO}", i + "");
					OBX = OBX.replace("{ID_AGENTE}", element.getIdsiap() + "");
					OBX = OBX.replace("{AGENTE}", element.getElement());
					
					switch (element.getAbbreviation()) {
					case "HGB":
						if (s.getMCH() != null){
							OBX = OBX.replace("{VALOR_R}", s.getHGB());
							OBX = OBX.replace("{R_SUP}", s.getHGB_H());
							OBX = OBX.replace("{R_INF}", s.getHGB_L());
							OBX = OBX.replace("{UNIDAD}", "g/dl");
						}						
						break;
					case "MCH":
						if (s.getMCH() != null){
							OBX = OBX.replace("{VALOR_R}", s.getMCH());
							OBX = OBX.replace("{R_SUP}", s.getMCH_H());
							OBX = OBX.replace("{R_INF}", s.getMCH_L());
							OBX = OBX.replace("{UNIDAD}", "pg");
						}						
						break;
					case "MCHC":
						if (s.getMCHC() != null){
							OBX = OBX.replace("{VALOR_R}", s.getMCHC());
							OBX = OBX.replace("{R_SUP}", s.getMCHC_H());
							OBX = OBX.replace("{R_INF}", s.getMCHC_L());
							OBX = OBX.replace("{UNIDAD}", "g/dl");
						}						
						break;
					case "RBC":
						if (s.getRBC() != null){
							OBX = OBX.replace("{VALOR_R}", s.getRBC());
							OBX = OBX.replace("{R_SUP}", s.getRBC_H());
							OBX = OBX.replace("{R_INF}", s.getRBC_L());
							OBX = OBX.replace("{UNIDAD}", "10^12/l");
						}						
						break;
					case "MVC":
						if (s.getMCV() != null){
							OBX = OBX.replace("{VALOR_R}", s.getMCV());
							OBX = OBX.replace("{R_SUP}", s.getMCV_H());
							OBX = OBX.replace("{R_INF}", s.getMCV_L());
							OBX = OBX.replace("{UNIDAD}", "fl");
						}						
						break;
					case "HCT":
						if (s.getHCT() != null){
							OBX = OBX.replace("{VALOR_R}", s.getHCT());
							OBX = OBX.replace("{R_SUP}", s.getHCT_H());
							OBX = OBX.replace("{R_INF}", s.getHCT_L());
							OBX = OBX.replace("{UNIDAD}", "%");
						}		
						break;
					case "RDW%":						
						break;
					case "WBC":
						if (s.getWBC() != null){
							OBX = OBX.replace("{VALOR_R}", s.getWBC());
							OBX = OBX.replace("{R_SUP}", s.getWBC_H());
							OBX = OBX.replace("{R_INF}", s.getWBC_L());
							OBX = OBX.replace("{UNIDAD}", "10^9/l");
						}
						break;
					case "LYM":
						break;
					case "LYM%":						
						break;
					case "MID":
						break;
					case "MID%":						
						break;
					case "GRA":
						break;
					case "GRA%":						
						break;
					case "PLT":
						if (s.getPLT() != null){
							OBX = OBX.replace("{VALOR_R}", s.getPLT());
							OBX = OBX.replace("{R_SUP}", s.getPLT_H());
							OBX = OBX.replace("{R_INF}", s.getPLT_L());
							OBX = OBX.replace("{UNIDAD}", "10^9/l");
						}
						break;
					case "MPV":
						if (s.getMPV() != null){
							OBX = OBX.replace("{VALOR_R}", s.getMPV());
							OBX = OBX.replace("{R_SUP}", s.getMPV_H());
							OBX = OBX.replace("{R_INF}", s.getMPV_L());
							OBX = OBX.replace("{UNIDAD}", "fl");
						}
						break;
					}
					
					i++;
				}
				
				System.out.println(s);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static void main(String[] args) {
		new XMLProcessor().XMLToObject(Constans.FILE_PATH);
	}

}