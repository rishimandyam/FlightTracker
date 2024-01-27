// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am


import java.util.*;
/**
 * this interface is used to model airport objects
 *
 * @author vishesh
 *
 */
public interface IAirport {

    //constructor parameters: String name, String filePath, ITime curTime

    /**
     * method allows users to add a plane to the arrivals tree
     *
     * @param plane the plane to add to arrivals
     */
    public void addArrival(IAirplane plane);

    /**
     * method allows users to add a plane to the departures tree
     *
     * @param plane the plane to add to departures
     */
    public void addDeparture(IAirplane plane);

    /**
     * method allows users to remove a plane from the arrivals tree
     *
     * @param plane the plane to remove from arrivals
     */
    public void removeArrival(IAirplane plane);

    /**
     * method allows users to remove a plane from the departures tree
     *
     * @param plane the plane to remove from departures
     */
    public void removeDeparture(IAirplane plane);

    /**
     * method allows both arrival and departure trees to be updated to reflect planes that have taken
     * off and landed
     */
    public void updateArrivalsAndDepartures(ITime curTime);

    /**
     * method allows users to access the arrivals tree
     * @return AirplaneRBT the arrivals tree
     */
    public IAirplaneRBT getArrivalsTree();

    /**
     * method allows users to access the departures tree
     * @return AirplaneRBT the arrivals tree
     */
    public IAirplaneRBT getDeparturesTree();

    /**
     * method allows users to set up trees
     * @param flights arraylist of flights
     * */
    public void setUpTrees(ArrayList<IAirplane> flights, ITime curTime);

    /**
     * method allows users to get 3 last arrived planes
     * @return ArrayList<Airplane> list of airplanes
     */
    public ArrayList<IAirplane> getPastArrivals();

    /**
     * method allows users to get 3 last departed planes
     * @return ArrayList<Airplane> list of airplanes
     */
    public ArrayList<IAirplane> getPastDepartures();

    /**
     * method allows users to get next 10 arriving planes
     * @return ArrayList<Airplane> list of airplanes
     */
    public ArrayList<IAirplane> getNextArrivals();

    /**
     * method allows users to get next 10 departing planes
     * @return ArrayList<Airplane> list of airplanes
     */
    public ArrayList<IAirplane> getNextDepartures();

}
