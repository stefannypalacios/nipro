package sv.com.nipro.interfaz;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import sv.com.nipro.interfaz.utils.XMLProcessor;

public class XmlToObjectTest {
	private static final String PATH = "C:\\BM-105419_2018-08-23.xml";

	@Test
	public void testXmlToObject() throws JAXBException, FileNotFoundException {

		new XMLProcessor().XMLToObject(PATH);
	}
}
