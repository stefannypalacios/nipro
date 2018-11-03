package sv.com.nipro.interfaz.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sv.com.nipro.interfaz.dto.Hl7DTO;
import sv.com.nipro.interfaz.dto.Sample;
import sv.com.nipro.interfaz.dto.Samples;
import sv.com.nipro.interfaz.entities.Element;
import sv.com.nipro.interfaz.entities.Employee;


public class XMLProcessor {
	public Samples XMLToObject(String path) {
		Samples object = null;
		try {
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Samples.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (Samples) jaxbUnmarshaller.unmarshal(file);
			//System.out.println(object);
			Employee employee = new Employee(); //simulando usuario en sesión
			
			// ******************************INICIO**********************************
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat fmtHL7 = new SimpleDateFormat(Constans.FORMAT_DATE_HL7);
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(Constans.FORMAT_DATE_HL7); 
			 
						
			Hl7DTO hl7 = new Hl7DTO();
			hl7.setMSH(Constans.MSH);
			hl7.setORC(Constans.ORC);
			hl7.setOBR(Constans.OBR);
			
			//Segmento MSH
			
			//AMBOS CAMPOS ESTÁN PRESENTES EN LA SOLICITUD
			hl7.setMSH(hl7.getMSH().replace("{SUMINISTRANTE_ID}", 11111111 + "")); //preguntar id asignado a nipro
			hl7.setMSH(hl7.getMSH().replace("{SUMINISTRANTE}", "NIPRO")); //preguntar 			
			
			
			//Fin segmento MSH
			
			
			//Segmento ORC		
			
			//Da la impresión de que solicitan cambios en un resultado ¿?
			hl7.setORC(hl7.getORC().replace("{COD_CTLR}", "NW")); //NW = nuevo, XO = Cambio
			hl7.setORC(hl7.getORC().replace("{ID_SOLICITUD}", "{ID_SOLICITUD}")); //Aún no se de donde sacarlo
			
			LocalDateTime now = LocalDateTime.now(); 
			hl7.setORC(hl7.getORC().replace("{FECHA_ENVIO}", dtf.format(now)));
			
			hl7.setORC(hl7.getORC().replace("{COD_EMPLEADO}", employee.getCode()));
			hl7.setORC(hl7.getORC().replace("{EMPLEADO}", employee.getName() + " " + employee.getSurname()));
			//Fin segmento ORC
			
			
			//Segmento OBR
			
			hl7.setOBR(hl7.getOBR().replace("{COD_EMPLEADO}", employee.getCode()));
			hl7.setOBR(hl7.getOBR().replace("{EMPLEADO}", employee.getName() + " " + employee.getSurname()));
			hl7.setOBR(hl7.getOBR().replace("{ID_EXAMEN_SOL}", "74036")); //No estoy seguro si cambia (siempre es examen de sangre)
			//Fin segmento OBR
			
			
			
			//Llenar lista con todos los elementos
			List<Element> lstEl = new ArrayList<Element>();
			String OBX;
			
			OBX = Constans.OBX_1;
			//TODO: ID del resultado cualitativo SEPS ??
			OBX = OBX.replace("{ID_RESULTADO}", "1");
			OBX = OBX.replace("{RESULTADO}", "Normal");
			
			System.out.println(OBX);		
			
			hl7.getOBXLlst().add(OBX);
			
			for (Sample s : object.getSample()) {
				boolean add = true;
				OBX = Constans.OBX;
				int i = 2;
				if (s.getDATE() != null) {
					System.out.println(s.getDATE());
					s.setDATE(s.getDATE().replace("T", " "));
				}
				
				Date date = formatter.parse(s.getDATE());				
				hl7.setMSH(hl7.getMSH().replace("{FECHA_GEN}", fmtHL7.format(date))); //Sección en MSH
				hl7.setOBR(hl7.getOBR().replace("{FECHA_RP_FINAL}", fmtHL7.format(date))); //Sección OBR
				hl7.setOBR(hl7.getOBR().replace("{FECHA_GENERADO}", fmtHL7.format(date))); //Sección OBR
				
				
				for (Element element : lstEl) {
					add = true;
					
					OBX = OBX.replace("{AUTOINCREMENTO}", i + "");
					OBX = OBX.replace("{ID_AGENTE}", element.getIdsiap() + "");
					OBX = OBX.replace("{AGENTE}", element.getElement());
					OBX = OBX.replace("{FECHA_RESULT}", fmtHL7.format(date));
					
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
						add = false;
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
						add = false;
						break;
					case "LYM%":
						add = false;
						break;
					case "MID":
						add = false;
						break;
					case "MID%":
						add = false;
						break;
					case "GRA":
						add = false;
						break;
					case "GRA%":
						add = false;
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
					
					if (add) i++;
				}
				
				System.out.println(OBX);
				if (add) {
					hl7.getOBXLlst().add(OBX);
				}
			}
			
			// ******************************FIN**********************************
			
			System.out.println(hl7.getHL7());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static void main(String[] args) {
		new XMLProcessor().XMLToObject(Constans.FILE_PATH);
	}

}