package sv.com.nipro.interfaz.utils;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sv.com.nipro.interfaz.entities.Samples;

public class XMLProcessor {
	public Samples XMLToObject(String path) {
		Samples object = null;
		try {
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Samples.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (Samples) jaxbUnmarshaller.unmarshal(file);
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public static void main(String[] args) {
		new XMLProcessor().XMLToObject(Constans.FILE_PATH);
	}

}