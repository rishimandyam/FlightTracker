// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * class models airports with trees for arrivals and departures over a day
 */
public class Airport implements IAirport {

    // RBT for arrivals and departures
    private IAirplaneRBT arrivalsTree;
    private IAirplaneRBT departuresTree;

    // arraylist for arrived and departed planes
    ArrayList<IAirplane> arrivedPlanesList;
    ArrayList<IAirplane> departedPlanesList;

    // create an AirplaneLoader
    IAirplaneLoader airplaneLoader;

    // arraylists for all arrivals and departures (flights) in a day
    ArrayList<IAirplane> flights;

    // airport name
    private String name = "";

    /**
     * airport constructor
     *
     * @param name name of airport
     */
    public Airport(String name, String filePath, ITime curTime) throws FileNotFoundException {

        // set name
        this.name = name;
        // create rbt
        this.arrivalsTree = new AirplaneRBT();
        this.departuresTree = new AirplaneRBT();

        // create arraylists for arrived and departed planes
        this.arrivedPlanesList = new ArrayList<IAirplane>();
        this.departedPlanesList = new ArrayList<IAirplane>();

        // set airplane loader
        this.airplaneLoader = new AirplaneLoader();

        // set flights: uncomment when airplane loader class works
        this.flights = this.airplaneLoader.loadAirport(filePath);

        // call set up trees method: uncomment when airplane loader class works
        this.setUpTrees(this.flights, curTime);
    }

    /**
     * method allows users to aget airport name
     *
     * @return String airport name
     */
    public String getName() {
        return this.name;
    }

    /**
     * method allows users to add a plane to the arrivals tree
     *
     * @param plane the plane to add to arrivals
     */
    @Override
    public void addArrival(IAirplane plane) {
        this.arrivalsTree.insert(plane);
    }

    /**
     * method allows users to add a plane to the departures tree
     *
     * @param plane the plane to add to departures
     */
    @Override
    public void addDeparture(IAirplane plane) {
        this.departuresTree.insert(plane);
    }

    /**
     * method allows users to remove a plane from the arrivals tree
     *
     * @param plane the plane to remove from arrivals
     */
    @Override
    public void removeArrival(IAirplane plane) {
        this.arrivalsTree.remove(plane);
    }

    /**
     * method allows users to remove a plane from the departures tree
     *
     * @param plane the plane to remove from departures
     */
    @Override
    public void removeDeparture(IAirplane plane) {
        this.departuresTree.remove(plane);
    }

    /**
     * method allows both arrival and departure trees to be updated to reflect
     * planes that have taken
     * off and landed
     */
    @Override
    public void updateArrivalsAndDepartures(ITime curTime) {
        this.updateArrivals(curTime);
        this.updateDepartures(curTime);
    }

    /**
     * private helper method to update arrivals based on time
     *
     * @param curTime current time
     */
    private void updateArrivals(ITime curTime) {

        // find if a plane with arrival time of curTime exists in arrival tree
        if (this.arrivalsTree.getByTime(curTime) != null) {
            IAirplane arrivedPlane = this.arrivalsTree.getByTime(curTime);

            // add plane to arrived plane arraylist
            this.arrivedPlanesList.add(arrivedPlane);

            // make sure there are at max 3 planes in the arrivedPlaneslist
            while (arrivedPlanesList.size() > 3) {
                arrivedPlanesList.remove(0);
            }

            // remove the arrivedPlane from the Arrivals tree
            this.removeArrival(arrivedPlane);
        }
    }

    /**
     * private helper method to update arrivals based on time
     *
     * @param curTime current time
     */
    private void updateDepartures(ITime curTime) {

        // find if a plane with departure time of curTime exists in departures tree
        if (this.departuresTree.getByTime(curTime) != null) {
            IAirplane arrivedPlane = this.departuresTree.getByTime(curTime);

            // add plane to departed plane arraylist
            this.departedPlanesList.add(arrivedPlane);

            // make sure there are at max 3 planes in the departed plane list
            while (departedPlanesList.size() > 3) {
                departedPlanesList.remove(0);
            }

            // remove the arrivedPlane from the departures tree
            this.removeDeparture(arrivedPlane);
        }
    }

    /**
     * method allows users to access the arrivals tree
     *
     * @return AirplaneRBT the arrivals tree
     */
    @Override
    public IAirplaneRBT getArrivalsTree() {
        return this.arrivalsTree;
    }

    /**
     * method allows users to access the departures tree
     *
     * @return AirplaneRBT the departures tree
     */
    @Override
    public IAirplaneRBT getDeparturesTree() {
        return this.departuresTree;
    }

    /**
     * method allows users to set up trees
     *
     * @param flights arraylist of flights
     */
    public void setUpTrees(ArrayList<IAirplane> flights, ITime curTime) {
        // check if its arrival or departure, and add to correct tree
        for (int i = 0; i < flights.size(); i++) {
            // if it is an arrival
            if (flights.get(i).getIsArrival()) {
                // make sure flight is still in route
                if (flights.get(i).isInRoute(curTime)) {
                    this.addArrival(flights.get(i));
                } else {
                    // if not in route add plane to arrived plane arraylist
                    this.arrivedPlanesList.add(flights.get(i));

                    // make sure there are at max 3 planes in the arrivedPlaneslist
                    while (this.arrivedPlanesList.size() > 3) {
                        this.arrivedPlanesList.remove(0);
                    }
                }
            }
            // if it is a departure
            else {
                // make sure flight is still in route
                if (flights.get(i).isInRoute(curTime)) {
                    this.addDeparture(flights.get(i));
                } else {
                    // if not in route, add plane to departed plane arraylist
                    this.departedPlanesList.add(flights.get(i));

                    // make sure there are at max 3 planes in the departed plane list
                    while (this.departedPlanesList.size() > 3) {
                        this.departedPlanesList.remove(0);
                    }
                }
            }
        }
    }

    /**
     * method allows users to get 3 last arrived planes
     *
     * @return ArrayList<Airplane> list of airplanes
     */
    @Override
    public ArrayList<IAirplane> getPastArrivals() {
        return arrivedPlanesList;
    }

    /**
     * method allows users to get 3 last departed planes
     *
     * @return ArrayList<Airplane> list of airplanes
     */
    @Override
    public ArrayList<IAirplane> getPastDepartures() {
        return departedPlanesList;
    }

    /**
     * method allows users to get next 10 arriving planes
     *
     * @return ArrayList<Airplane> list of airplanes
     */
    @Override
    public ArrayList<IAirplane> getNextArrivals() {
        // get in order arraylist of planes in arrival tree
        ArrayList<IAirplane> inOrder = this.arrivalsTree.inOrderPlanes();

        // find the limit for how many planes to show: smallest number between 10 and
        // inOrder.size()
        int limit = 7;
        // check if size is smaller than 10, if so, the size is now limit
        if (7 > inOrder.size()) {
            limit = inOrder.size();
        }

        // create arraylist for next set of arrivals
        ArrayList<IAirplane> nextArrivals = new ArrayList<IAirplane>();

        // copy over planes from inOrder to new arraylist for the next arrivals
        for (int i = 0; i < limit; i++) {
            nextArrivals.add(inOrder.get(i));
        }

        // return the arraylist of the next arrivals
        return nextArrivals;
    }

    /**
     * method allows users to get next 10 departing planes
     *
     * @return ArrayList<Airplane> list of airplanes
     */
    @Override
    public ArrayList<IAirplane> getNextDepartures() {
        // get in order arraylist of planes in departure tree
        ArrayList<IAirplane> inOrder = this.departuresTree.inOrderPlanes();

        // find the limit for how many planes to show: smallest number between 10 and
        // inOrder.size()
        int limit = 7;
        // check if size is smaller than 10, if so, the size is now limit
        if (7 > inOrder.size()) {
            limit = inOrder.size();
        }

        // create new arraylist for the next set of departures
        ArrayList<IAirplane> nextDepartures = new ArrayList<IAirplane>();

        // copy over planes from inOrder to new arraylist for the next departures
        for (int i = 0; i < limit; i++) {
            nextDepartures.add(inOrder.get(i));
        }

        // return next departures
        return nextDepartures;
    }

    public ArrayList<IAirplane> searchByPeriod(ITime time1, ITime time2) {
        return (ArrayList<IAirplane>) departuresTree.searchByPeriod(time1, time2);
    }

}
