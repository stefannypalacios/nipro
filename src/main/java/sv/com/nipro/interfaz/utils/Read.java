package sv.com.nipro.interfaz.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Read {

	public static void main(String[] args) {
		System.out.println("INICIO");
		try {

			String content = readFile(Constans.FILE_PATH, StandardCharsets.UTF_8);
			System.out.println(content);

			Document doc = Jsoup.parse(content);
			Elements smpresults = doc.select("smpresults");

			for (Element el : smpresults) {
				// System.out.println(el.html() + "\n");

				Elements agents = el.getElementsByTag("n");
				Elements values = el.getElementsByTag("v");
				Elements lowerLimit = el.getElementsByTag("l");
				Elements upperLimit = el.getElementsByTag("h");

				for (int i = 0; i < agents.size(); i++) {
					System.out.println(
							"Agente: " + agents.get(i).text() + "\tValor: " + values.get(i).text() + "\tLim. Inf.: "
									+ lowerLimit.get(i).text() + "\t\tLim. Sup.: " + upperLimit.get(i).text());
				}

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
