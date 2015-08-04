import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLqueryParser {
	@SuppressWarnings("null")
	public static void main(String[] args) 
	{
		ArrayList<String> weather = new ArrayList<String>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=-70.6723&unit=0&lg=english&FcstType=dwml");
			Element root = doc.getDocumentElement();
			
			NodeList conditions = root.getElementsByTagName("weather-conditions");
			/*This output will be a week's worth of weather conditions for every 12 hours */
										
			for(int i=0; i<14; i++) //conditions.getLength()
			{
				Element condition = (Element)conditions.item(i); // The 2 last "weather-conditions" are irrelevant
				System.out.printf("Condition " + (i+1) + ": %s%n", condition.getAttribute("weather-summary"));
				weather.add(condition.getAttribute("weather-summary"));				
			}
			//System.out.println(weather);
					
		} 
		catch (ParserConfigurationException | SAXException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
