// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

/**
 * Instances of classes that implement this interface represents a single
 * airplane information object that can be stored, sorted, and searched for based on 
 * these accessors below.
 */
import java.util.*;

/**
 * this interface is used to model airplane objects
 */
public interface IAirplane extends Comparable<IAirplane> {
    // constructor args (boolean isArrival, String flightID, String airport, ITime planeTime, String company)

	/**
	 * method checks if the plane is an arrival or not
	 * @return true if plane is an arrival, false otherwise
	 */
	public boolean getIsArrival();

	/**
	 * method retrieves the flight's id
	 * @return String flightID
	 */
	public String getFlightID();

	/**
	 * method gets the airport that the plane either is going to or coming from
	 * @return String airport
	 */
	public String getAirport();

	/**
	 * method gets the planes arrival or departure time
	 * @return ITime object for the time
	 */
	public ITime getTime();

	/**
	 * method gets the planes arrival or departure time
	 * @return String for the time
	 */
	public String getTimeAsString();

	/**
	 * method gets the planes company, ie delta, american, etc
	 * @return String of company
	 */
	public String getCompany();

	/**
	 * method compares the plane's time to the current time to see if plane has arrived/departed yet
	 * @param curTime the current time
	 * @return true if plane is still waiting to arrive/depart, false otherwise
	 */
	public boolean isInRoute(ITime curTime);

	/**
	 * method parses the airplane object and returns a string representation of it
	 * @return String representation of the airplane object
	 */
	public String toString();
	
}

