import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;*/

public class XML_docDOM2
{
    public static void main(String[] args) throws Exception
    {
        try{
            //Establish connection with the website
            URL url = new URL("http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=-70.6723&unit=0&lg=english&FcstType=dwml");
            HttpURLConnection requestPG = (HttpURLConnection) url.openConnection();
            requestPG.setRequestMethod("GET");
            requestPG.connect();

            //BufferedReader reads from given URL
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestPG.getInputStream()));



            //Create a file, BufferedWriter and FileWriter to write to file
            File file = new File("weather.xml");
            FileWriter write2file = new FileWriter(file);
            BufferedWriter out  = new BufferedWriter(write2file);

            //Read lines from website
            String theLine;

            while ((theLine = bufferedReader.readLine()) != null)
            {
                out.write(theLine);
                out.newLine();
            }
            out.close();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        //Document doc = docBuilder.parse(XmlFile);

    }
}
