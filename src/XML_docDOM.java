import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;

//import org.w3c.dom.Document;

public class XML_docDOM
{    
	public static void main(String[] args) throws Exception
	{
		//establish connection with the site
		URL url = new URL("http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=-70.6723&unit=0&lg=english&FcstType=dwml");
		HttpURLConnection requestPG = (HttpURLConnection) url.openConnection();
		requestPG.setRequestMethod("GET");
		requestPG.connect();
		
		//BufferedReader reads from url
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestPG.getInputStream()));
		//String line;
		
	
		//scan xml and place it into new file
		File file = new File("weather.xml");
		FileWriter write2file = new FileWriter(file);
		BufferedWriter out  = new BufferedWriter(write2file);
        
		//read lines from site
		while ((bufferedReader.readLine()) != null) 
		{
			out.write(bufferedReader.readLine());
			//System.out.print(line +  "\n");
		}
		out.close();
		
		//Document doc = docBuilder.parse(XmlFile);

	}
}
