import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLqueryParser 
{
	public static void main(String[] args) 
	{
		//Change hour, minutes, second here
		Calendar today = Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 13);
		today.set(Calendar.MINUTE, 29);
		today.set(Calendar.SECOND, 0);
		// Every night at 3am run task
		Timer timer = new Timer();
		timer.schedule(new GetForecastDecision(), today.getTimeInMillis(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));	
	}
}

class GetForecastDecision extends TimerTask
{
	public static void run(String[] args) 
	{
		ArrayList<String> weather = new ArrayList<String>();  // This will take the next 7 days' weather condition as an array.
		// The following are the possible "wet conditions" that will tell if the sprinkler should or no go on.
		String[] wetConditions = {"Rain Showers", "Light Rain Showers", "Light Rain and Breezy", "Heavy Rain Showers", 
				"Rain Showers in Vicinity", "Light Showers Rain", "Heavy Showers Rain", "Showers Rain",
				"Showers Rain in Vicinity", "Rain Showers Fog/Mist", "Light Rain Showers Fog/Mist", 
				"Heavy Rain Showers Fog/Mist", "Rain Showers in Vicinity Fog/Mist", "Light Showers Rain Fog/Mist", 
				"Heavy Showers Rain Fog/Mist", "Showers Rain Fog/Mist", "Showers Rain in Vicinity Fog/Mist", "Thunderstorm", 
				" Thunderstorm Rain", "Light Thunderstorm Rain", "Heavy Thunderstorm Rain", "Thunderstorm Rain Fog/Mist", 
				"Light Thunderstorm Rain Fog/Mist", "Heavy Thunderstorm Rain Fog and Windy", "Heavy Thunderstorm Rain Fog/Mist",
				"Thunderstorm Showers in Vicinity", "Light Thunderstorm Rain Haze", "Heavy Thunderstorm Rain Haze", "Thunderstorm Fog", 
				"Light Thunderstorm Rain Fog", "Heavy Thunderstorm Rain Fog", "Thunderstorm Light Rain", "Thunderstorm Heavy Rain", 
				"Thunderstorm Rain Fog/Mist", "Thunderstorm Light Rain Fog/Mist", "Thunderstorm Heavy Rain Fog/Mist", 
				"Thunderstorm in Vicinity Fog/Mist", "Thunderstorm Showers in Vicinity", "Thunderstorm in Vicinity Haze", 
				"Thunderstorm Haze in Vicinity", "Thunderstorm Light Rain Haze", "Thunderstorm Heavy Rain Haze", "Thunderstorm Fog", 
				"Thunderstorm Light Rain Fog", "Thunderstorm Heavy Rain Fog", "Thunderstorm Hail", "Light Thunderstorm Rain Hail", 
				"Heavy Thunderstorm Rain Hail", "Thunderstorm Rain Hail Fog/Mist", "Light Thunderstorm Rain Hail Fog/Mist", 
				"Heavy Thunderstorm Rain Hail Fog/Hail", "Thunderstorm Showers in Vicinity Hail", "Light Thunderstorm Rain Hail Haze", 
				"Heavy Thunderstorm Rain Hail Haze", "Thunderstorm Hail Fog", "Light Thunderstorm Rain Hail Fog", 
				"Heavy Thunderstorm Rain Hail Fog", "Thunderstorm Light Rain Hail", "Thunderstorm Heavy Rain Hail", 
				"Thunderstorm Rain Hail Fog/Mist", "Thunderstorm Light Rain Hail Fog/Mist", "Thunderstorm Heavy Rain Hail Fog/Mist", 
				"Thunderstorm in Vicinity Hail", "Thunderstorm in Vicinity Hail Haze", "Thunderstorm Haze in Vicinity Hail", 
				"Thunderstorm Light Rain Hail Haze", "Thunderstorm Heavy Rain Hail Haze", "Thunderstorm Hail Fog", 
				"Thunderstorm Light Rain Hail Fog", "Thunderstorm Heavy Rain Hail Fog", "Thunderstorm Small Hail/Snow Pellets", 
				"Thunderstorm Rain Small Hail/Snow Pellets", "Light Thunderstorm Rain Small Hail/Snow Pellets", 
				"Heavy Thunderstorm Rain Small Hail/Snow Pellets", "Freezing Rain Rain", "Light Freezing Rain Rain", 
				"Heavy Freezing Rain Rain", "Rain Freezing Rain", "Light Rain Freezing Rain", "Heavy Rain Freezing Rain", 
				"Freezing Drizzle Rain", "Light Freezing Drizzle Rain", "Heavy Freezing Drizzle Rain", " Rain Freezing Drizzle", 
				"Light Rain Freezing Drizzle", "Heavy Rain Freezing Drizzle", "Thunderstorm in Vicinity", "Thunderstorm in Vicinity Fog", 
				"Thunderstorm in Vicinity Haze", "Light Rain", "Drizzle", "Light Drizzle", "Heavy Drizzle", "Light Rain Fog/Mist", 
				"Drizzle Fog/Mist", "Light Drizzle Fog/Mist", "Heavy Drizzle Fog/Mist", "Light Rain Fog", "Drizzle Fog", "Light Drizzle Fog", 
				"Heavy Drizzle FogRain", "Heavy Rain", "Rain Fog/Mist", "Heavy Rain Fog/Mist", "Rain Fog", "Heavy Rain Fog", 
				"Slight Chance Rain then Rain Likely"};
		
		try 
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// This is the URL with the XML with Duxbury, Massachusett's weather information, which will be parsed.
			Document doc = builder.parse("http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=-70.6723&unit=0&lg=english&FcstType=dwml");
			Element root = doc.getDocumentElement();
			
			NodeList conditions = root.getElementsByTagName("weather-conditions");
			// This output will be a week's worth of weather conditions for every 12 hours.
										
			for(int i=0; i<2; i++) 
				/*conditions.getLength() would take all the available info, but I am only interested in the next 24 hrs.*/ 
				{
					Element condition = (Element)conditions.item(i); 
					System.out.printf("Condition " + (i+1) + ": %s%n", condition.getAttribute("weather-summary"));
					weather.add(condition.getAttribute("weather-summary"));			
				}

				boolean rain = false;
				if ((Collections.disjoint(weather, Arrays.asList(wetConditions)) == false))	//(weather.equals(statement))
				{	
						rain = true;
				}	
				ResultingRespose(rain);
			} 
			
			catch (ParserConfigurationException | SAXException | IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void ResultingRespose(boolean rain) {
			if (rain == false)
			{
				System.out.println("The sprinkler will not turn for the next 24 hours");
			}
			else
			{
				System.out.println("The sprinkler system will now come on.");
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
	}
	 
