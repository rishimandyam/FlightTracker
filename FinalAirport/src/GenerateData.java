import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.*;

public class GenerateData implements IGenerateData {
    String filePath;
    HashSet<String> timeSet = new HashSet<>();

    public GenerateData(String filePath) {
        this.filePath = filePath + "\\flightData.xml";
    }

    /**
     * Writes airplane data in XML format to flightData.xml
     */
    @Override
    public void createData() {
        int numPlanes = 100;

        // Creating a fileWriter
        FileWriter fw;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            // students root element
            Element rootElement = doc.createElement("data");
            doc.appendChild(rootElement);

            // Creating arrivals
            for (int i = 0; i < numPlanes; i++) {
                // Creating flight tag
                Element flight = doc.createElement("flight");
                // Creating arrival tag
                Element isArrival = doc.createElement("isArrival");
                // Creating Airport tag
                Element airport = doc.createElement("airport");
                // Creating Time tag
                Element time = doc.createElement("time");

                // Setting attributes
                rootElement.appendChild(flight);
                flight.setAttribute("company", randomCompany());
                flight.setAttribute("id", randomID());

                // Setting child references
                flight.appendChild(isArrival);
                flight.appendChild(airport);
                flight.appendChild(time);

                // Setting child values
                isArrival.setTextContent("true");
                airport.setTextContent(randomAirport());
                time.setTextContent(generateUniqueITime().toString());
            }

            // Creating departures
            for (int i = 0; i < numPlanes; i++) {
                // Creating flight tag
                Element flight = doc.createElement("flight");
                // Creating arrival tag
                Element isArrival = doc.createElement("isArrival");
                // Creating Airport tag
                Element airport = doc.createElement("airport");
                // Creating Time tag
                Element time = doc.createElement("time");

                // Setting attributes
                rootElement.appendChild(flight);
                flight.setAttribute("company", randomCompany());
                flight.setAttribute("id", randomID());

                // Setting child references
                flight.appendChild(isArrival);
                flight.appendChild(airport);
                flight.appendChild(time);

                // Setting child values
                isArrival.setTextContent("false");
                airport.setTextContent(randomAirport());
                time.setTextContent(generateUniqueITime().toString());
            }

            // Writing the content to the XML file
            // Write the content into XML file
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("flightData.xml"));

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // Beautify the format of the resulted XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException e) {
            e.getStackTrace();
        }
    }

    /**
     * Returns the path to the XML file containing the flight data
     * 
     * @return the file path
     */
    @Override
    public String getPath() {
        // TODO Auto-generated method stub
        return filePath;
    }

    private String generateUniqueITime() {
        String newTime;
        while (true) {
            newTime = newTimeHelper();
            if (!timeSet.contains(newTime)) {
                timeSet.add(newTime);
                break;
            }
        }
        return newTime;
    }

    private String newTimeHelper() {
        String newTime;
        Random rand = new Random();
        String min = String.valueOf(rand.nextInt(60));
        String hour = String.valueOf(rand.nextInt(24));

        newTime = hour + ":" + min;
        return newTime;
    }

    /**
     * method psuedorandomlly chooses an airport from an array of airports
     * 
     * @return String of airport
     */
    private String randomAirport() {
        String[] airport = { "ATL", "DFW", "DEN", "ORD", "LAX", "CLT", "LAS", "PHX", "MCO", "JFK", "MSN", "FAT", "BUR",
                "LAX", "SJC", "SAN", "DAB", "PNS", "AUG", "DET", "JPN", "ABQ", "BUF", "ALB", "HST" };

        Random random = new Random();
        String newAirport = airport[random.nextInt(airport.length)];
        return newAirport;
    }

    /**
     * method randomly create an 4-digit flight ID
     * 
     * @return string code
     */

    private String randomID() {
        Random random = new Random();
        String id = String.valueOf((random.nextInt(10)));
        for (int i = 0; i < 3; i++)
            id = id.concat(String.valueOf((random.nextInt(10))));
        return id;
    }

    /**
     * method randomly select an Airline from the list
     * 
     * @return string code
     */
    private String randomCompany() {
        String[] company = { "Delta Air Lines", "Southwest Airlines", "United Airlines", "Alaska Airlines",
                "American Airlines",
                "JetBlue Airways", "Hawaiian Airlines", "Spirit Airlines", "Frontier", " Allegiant Airlines",
                "Turkish Airlines", "Air India", "British Airways" };
        Random random = new Random();
        return company[random.nextInt(company.length)];
    }
}
