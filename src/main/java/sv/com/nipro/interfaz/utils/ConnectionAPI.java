/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 *
 * @author Karina Palacios
 */
public class ConnectionAPI {

    private static final String URL = "";
    
    public String soapURLConnection(String messageReq, String wsReq) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("soapClient " + wsReq + " - " + messageReq);
        if (URL != null && !"".equals(URL)) {
            java.net.URL url = new java.net.URL(URL);
            java.net.URLConnection conn = url.openConnection();

            // Set the necessary header fields
            conn.setRequestProperty("SOAPAction", URL.concat("#").concat(wsReq));
            conn.setDoOutput(true);

            // Send the request
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(messageReq);
            wr.flush();
            // Read the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            String resp = readXMLresponse(result.toString());
            return resp;
        }
        return "";
    }
    
    public String readXMLresponse(String data) throws SAXException, IOException, ParserConfigurationException {
        System.out.println("readXMLresponse - " + data);
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource src = new InputSource();
        src.setCharacterStream(new StringReader(data));

        org.w3c.dom.Document doc = builder.parse(src);
        String resp = doc.getElementsByTagName("return") != null ? doc.getElementsByTagName("return").item(0).getTextContent() : "";
        return resp;
    }

	public String connection(String nameController, String body) throws UnsupportedEncodingException {

		StringBuilder result = new StringBuilder();
		String line = "";
		HttpResponse response = null;

		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(URL + nameController);
			post.setHeader("Content-type", "application/json");
			post.setHeader("User-Agent", USER_AGENT);
			post.setEntity(new StringEntity(body));

			try {
				response = client.execute(post);
			} catch (IOException ex) {
				Logger.getLogger(ConnectionAPI.class.getName()).log(Level.SEVERE, null, ex);
			}

			BufferedReader rd = null;
			try {
				rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			} catch (IOException ex) {
				Logger.getLogger(ConnectionAPI.class.getName()).log(Level.SEVERE, null, ex);
			} catch (UnsupportedOperationException ex) {
				Logger.getLogger(ConnectionAPI.class.getName()).log(Level.SEVERE, null, ex);
			}

			try {
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
			} catch (IOException ex) {
				Logger.getLogger(ConnectionAPI.class.getName()).log(Level.SEVERE, null, ex);
			}
			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
			System.out.println("Resultado >>> " + response.toString());

		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(ConnectionAPI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result.toString();
	}

}
