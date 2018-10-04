package sv.com.nipro.interfaz.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import sv.com.nipro.interfaz.entities.Root;

public class XMLProcessor {
	public Root XMLToObject(String path) {
		Root object = null;
		try {
			
//			FileInputStream fis = new FileInputStream(path);
//
//			List<InputStream> streams = Arrays.asList(new ByteArrayInputStream("<root>".getBytes()), fis,
//					new ByteArrayInputStream("</root>".getBytes()));
//			
//			InputStream cntr = new SequenceInputStream(Collections.enumeration(streams));
//			System.out.println(cntr);
//			
			File file = new File(path);
			JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = (Root) jaxbUnmarshaller.unmarshal(file);
			System.out.println(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public void formatXML(String path) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);

		List<InputStream> streams = Arrays.asList(new ByteArrayInputStream("<root>".getBytes()), fis,
				new ByteArrayInputStream("</root>".getBytes()));
		
		InputStream cntr = new SequenceInputStream(Collections.enumeration(streams));
	}

}
