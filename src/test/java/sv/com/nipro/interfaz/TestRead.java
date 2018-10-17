package sv.com.nipro.interfaz;

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
import sv.com.nipro.interfaz.utils.Constans;

public class TestRead {

	public static void main(String[] args) {
		System.out.println("INICIO");
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

				List<Agent> objList = new ArrayList<Agent>();
				for (int i = 0; i < agents.size(); i++) {
					Agent agent = new Agent();
					agent.setName(agents.get(i).text());
					agent.setValue(values.get(i).text());
					agent.setLowerLimit(lowerLimit.get(i).text());
					agent.setUpperLimit(upperLimit.get(i).text());
					objList.add(agent);
				}

				System.out.println("objList --> "+objList);
				System.out.println("**************************************");
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
