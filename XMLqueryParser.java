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
			
			NodeList list = doc.getElementsByTagName("weather");
			System.out.println("There are " + list.getLength() + " items");
			
			for (int i = 0; i < list.getLength(); i++)
			{
				Element item = (Element)list.item(i);
				System.out.println(item.getFirstChild().getNodeValue());
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
