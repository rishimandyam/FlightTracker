// --== CS400 Project Two File Header ==--
// Name: Yun Li
// CSL Username: <yunl>
// Email: <li2459@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <>

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Instances of classes that implement this interface can be used to load a list
 * of airplanes objects from a specified XML source file.
 * Each piece of flight information is loaded into 2 Airplne object: one is
 * about the departure information and the other is of arrival information
 * of the particular fight
 * Flexibility: The loader excludes the information whose key information of
 * either departure or arrival is missing.
 */

/*
 * xml file sample
 * <Batch>
 * <flight Company=" Allegiant Airlines" ID="0379">
 * <Departure>MCO</Departure>
 * <Destination>ORD</Destination>
 * <TakeoffTime>2022-06-14T04:11</TakeoffTime>
 * <LandTime>2022-06-14T21:22</LandTime>
 * </flight>
 * </Batch>
 */

public class AirplaneLoader implements IAirplaneLoader {

    /**
     * Returns an arrayList containing all the planes
     * 
     * @param the filePath where the XML will be stored
     */
    @Override
    public ArrayList<IAirplane> loadAirport(String filePath) {
        GenerateData gd = new GenerateData(filePath);
        gd.createData();
        ArrayList<IAirplane> allPlanes = new ArrayList<>();

        try {
            allPlanes = loadAirplane(gd.getPath());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return allPlanes;
    }

    /**
     * loads airplane data into airplane objects, and then adds them to an arraylist
     * and returns it
     *
     * @param filePath, the filepath to the xml file with plane data
     * @return ArrayList<Airplane> of airplanes
     * @throws FileNotFoundException
     */
    private ArrayList<IAirplane> loadAirplane(String filePath) throws FileNotFoundException {

        // get file
        File file = new File(filePath);
        // create arraylist of planes to return
        ArrayList<IAirplane> airplaneList = new ArrayList<>();

        try {
            // create document builder factory and document builder
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();

            // save flights as nodes
            NodeList nodeList = doc.getElementsByTagName("flight");
            // iterate through the nodelist
            for (int i = 0; i < nodeList.getLength(); i++) {
                String isArrivalStr;
                String flightID;
                String airport;
                String company;
                String timeStr;
                Node curPlane = nodeList.item(i);

                // get the various data for the airplane objects, and store it in the above
                // variables
                if (curPlane.getNodeType() == Node.ELEMENT_NODE) {
                    Element curElement = (Element) curPlane;
                    company = curElement.getAttribute("company");
                    flightID = curElement.getAttribute("id");
                    airport = curElement.getElementsByTagName("airport").item(0).getTextContent();
                    timeStr = curElement.getElementsByTagName("time").item(0).getTextContent();
                    isArrivalStr = curElement.getElementsByTagName("isArrival").item(0).getTextContent();

                    // convert string to ITime and to boolean
                    ITime time = new Time(timeStr);
                    boolean isArrival = false;
                    if (isArrivalStr.equals("true")) {
                        isArrival = true;
                    }
                    // create new plane, and add it to the flights arraylist
                    Airplane newPlane = new Airplane(isArrival, flightID, airport, time, company);
                    airplaneList.add(newPlane);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return airplaneList;
    }

    // /**
    // *This method is a copy of loadAirplane taking random hardcode as input for
    // testing
    // * @param: A String for test
    // * @return: an ArrayList of Airplane object with complete departure or arrival
    // information
    // */
    // public ArrayList<IAirplane> loadAirplaneTest(String testline) {
    // ArrayList<IAirplane> list = new ArrayList<>();
    //
    // String line = testline;
    // String[] token = line.split("</flight>");
    // /////////********* ************/
    // if (Parse_Dep(token[0])!=null) {
    // list.add(Parse_Dep(token[0]));
    // }
    // if (Parse_arrive(token[0]) !=null) {
    // list.add(Parse_arrive(token[0]));
    // }
    // return list;
    // }

    // /**
    // *Helper method for parsing arrival informatiom for each flight
    // * @param: string
    // * @return: an Airplane object with complete arrival information
    // */
    //
    // private IAirplane Parse_arrive(String cline) {
    // boolean isArrival = true;
    // String flightID = null;
    // String company = null;
    // String airport = null;
    // ITime planeTime = null;
    //
    // String[] element = cline.split("><");
    // String[] atr = element[0].split("\"");
    //
    // for (int i = 0; i < atr.length; i++) {
    // if (atr[i].contains("Company")) {
    // company = atr[i + 1] ;
    //
    // }
    // if (atr[i].contains("ID")) {
    //
    // flightID = atr[i + 1];
    //
    // }
    // }
    //
    //
    // for (int j = 1; j < element.length; j++) {
    // String[] childlist = element[j].split("[<>]");
    // if (childlist[0].contains("Destination"))
    // airport = childlist[1];
    //
    // if (childlist[0].contains("LandTime")) {
    // String timestring = childlist[1];
    //
    // planeTime = createtime(timestring);
    // }
    //
    // }
    // //check any key information miss
    // if(flightID == null | company == null | airport == null | planeTime == null){
    // return null;
    // };
    // return new Airplane(isArrival, flightID, airport, planeTime, company);
    // }
    // /**
    // *Helper method for parsing departure informatiom for each flight
    // * @param: string
    // * @return: an Airplane object with complete departure information
    // */
    // private IAirplane Parse_Dep(String cline) {
    // boolean isArrival = false;
    // String flightID = null;
    // String company = null;
    // String airport = null ;
    // ITime planeTime = null ;
    //
    // String[] element = cline.split("><");
    //
    // String[] atr = element[0].split("\"");
    //
    //
    // for (int i = 0; i < atr.length; i++) {
    // if (atr[i].contains("Company")) {
    // company = atr[i + 1];
    // i++;
    // }
    // if (atr[i].contains("ID")) {
    // flightID = atr[i + 1];
    // i++;
    // }
    // }
    // // parse the rest
    // for (int j = 1; j < element.length; j++) {
    //
    // String[] childlist = element[j].split("[<>]");
    //
    // if (childlist[0].contains("Departure"))
    // airport = childlist[1];
    // if (childlist[0].contains("TakeoffTime")) {
    // String timestring = childlist[1];
    // planeTime = createtime(timestring);
    // }
    // }
    //
    // //check any key information miss
    // if(flightID == null | company == null | airport == null | planeTime == null){
    // return null;
    // }
    //
    // return new Airplane(isArrival, flightID, airport, planeTime, company);
    //
    // }
    //
    // /**
    // *Helper method for parsing time string
    // * @param: string
    // * @return: an Time object
    // */
    // private ITime createtime(String l) {
    // String[] time = l.split("[-T:]");
    // int year = Integer.parseInt(time[0]);
    // int month = Integer.parseInt(time[1]);
    // int date = Integer.parseInt(time[2]);
    // int hour = Integer.parseInt(time[3]);
    // int min = Integer.parseInt(time[4]);
    //
    // ITime planetime = new Time(hour, min);
    //
    // return planetime;
    // }

}
