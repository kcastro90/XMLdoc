# XMLdoc
June 23
    The idea behind this Java program was to create something that would be free, an open software, with potential of expanding, 
and that would decrease consumption of water as well as the financial cost for property owners with land in need of watering.  
The National Weather Service makes their forecast data available to the public and programmers as XML files based on the 
location’s latitude and longitude.  The proper URL to parse the correct XML was necessary and gathered, and the XML chosen to b
e queried corresponded to the town of Duxbury, Massachusetts, with its appropriate XML and URL for it. The libraries used were 
chosen by how practical they were to retrieve and make its data useful, although it was not the only possible way of doing so.
The goal for the program is to assure that land is being watered every other day.  Depending on weather conditions from the day 
prior to the forecast query and the next twenty four hours from the query, the sprinkler will either be told to turn on, or stay 
off, so the land is not watered excessively, but watered. A schedule was created with the use of libraries such as 
java.util.Timer, java.util.TimerTask, and util.concurrent.TimeUnit, to set the day/night that the weather should be checked, 
while checking the current time and date, and running the program right away in the first run, and long term from there on, at 
the set chosen time. Using a delay or putting a thread to sleep would have eventually interrupted a daily recurring and 
consistent query. Consequently, a fixed scheduled event was created to run every day from the day that the program is first 
executed.  The chosen time, early in the morning, at 3:00 am seemed the most appropriate to avoid catching any kids or any other 
person who is not expecting a sprinkler to turn on. 
    All the possible answers that show a fair amount of humidity/precipitation were found at the National Oceanic and Atmospheric 
Administration’s National Weather Service site http://w1.weather.gov/xml/current_obs/weather.php. The program queries the web 
page containing the XML file and instantly parses the next twenty four hours “String” values to see if it will rain. Before 
telling the sprinkler a decision whether or not it should go on, it checks an array that saves two truth values at a time and 
informs if there was rain in the previous day, to ensure that the goal of the land is watered every other day is followed. The 
first time the program runs, it cannot use a prior day’s information, so it is set to heavily make its judgment on the next 24 
hours response. As long as it doesn’t rain in the first execution, then it will send the “turn on” signal.
    The final decision is made with the use of logical statements that check Boolean values. The program’s limitation is that 
it only go as far as sending the on or off signals that are represented by a warning message of what will happen. Since there
are several types of existing sprinkler systems, each may have to receive a different type of connectivity or signal type.
Furthermore, some sprinklers have several sprinkler sections that turn on at different times, so such systems would require 
additional implementations.

Resources:

National Oceanic and Atmospheric Administration National Weather Service:
http://forecast.weather.gov/MapClick.php?lat=42.0418&lon=70.6723&unit=0&lg=english&FcstType=dwml
http://w1.weather.gov/xml/current_obs/weather.php.
