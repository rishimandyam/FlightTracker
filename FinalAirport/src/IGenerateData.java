// --== CS400 Project Two File Header ==--
// Name: Yun Li
// CSL Username: <yunl>
// Email: <li2459@wisc.edu>
// Lecture #: <001 @11:00am>
// Notes to Grader: <>

//reference1: https://mkyong.com/java/how-to-create-xml-file-in-java-dom/
///reference2: https://examples.javacodegeeks.com/core-java/xml/parsers/documentbuilderfactory/create-xml-file-in-java-using-dom-parser-example/
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.util.*;

/**
 * Instances of classes that implement this interface randomly generate airplane elements 
 * and stored them in a xml file 
 * Information are randomly generated or randomly assigned
 * default file path:
 * default number of elements:
 */


/**DATA structure
    <AIRPLANE>
        <LANDTIME> </LANDTIME>
        <TAKEOFFTIME> </TAKEOFFTIME>
        <FLIGHTID> </FLIGHTID>
        <DESTINATION> </DESTINATION>
        <DEPARTTURE> </DEPARTTURE>
    </AIRPLAN>
 *
 */

public interface IGenerateData {
	
	void createData();
	
	//return the file path where XML file locates
	String getPath();

}
