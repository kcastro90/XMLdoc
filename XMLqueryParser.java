import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLqueryParser {
	public static void main(String[] args) 
	{
		try {
			DocumentBuilderFactory factory = 
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=-70.6723&unit=0&lg=english&FcstType=dwml");
			Element root = doc.getDocumentElement();
			
			NodeList conditions =
					root.getElementsByTagName("weather-conditions");
					for(int i=0; i<14; i++) {
					Element condition = (Element)conditions.item(i);
					System.out.printf("Condition: %s%n", condition.getAttribute("weather-summary"));
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
