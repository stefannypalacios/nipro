package sv.com.nipro.interfaz.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import sv.com.nipro.interfaz.entities.Agent;

public class Read {

	private List<Agent> listAgents;
	
	public static void main(String[] args) {
		new Read().readFile();
	}

	public void readFile() {
		System.out.println("INICIO");
		listAgents = new ArrayList<Agent>();
		Agent agent = new Agent();
		try {

			String content = readFile(Constans.FILE_PATH, StandardCharsets.UTF_8);
			System.out.println(content);

			Document doc = Jsoup.parse(content);
			Elements smpresults = doc.select("smpresults");

			for (Element el : smpresults) {
				Elements agents = el.getElementsByTag("n");
				Elements values = el.getElementsByTag("v");
				Elements lowerLimit = el.getElementsByTag("l");
				Elements upperLimit = el.getElementsByTag("h");

				for (int i = 0; i < agents.size(); i++) {
					agent = new Agent();
					agent.setName(agents.get(i).text());
					agent.setValue(values.get(i).text());
					agent.setLowerLimit(lowerLimit.get(i).text());
					agent.setUpperLimit(upperLimit.get(i).text());
					listAgents.add(agent);
				}
				System.out.println("***** Agentes ******");
				System.out.println(listAgents);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

}
