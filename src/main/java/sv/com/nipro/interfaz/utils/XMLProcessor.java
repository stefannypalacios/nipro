package sv.com.nipro.interfaz.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sv.com.nipro.interfaz.dto.Hl7DTO;
import sv.com.nipro.interfaz.dto.Sample;
import sv.com.nipro.interfaz.dto.Samples;


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
			hl7.getOBXLlst().add(Constans.OBX_1);
			
			for (Sample s : object.getSample()) {
				String OBX = Constans.OBX;
				
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