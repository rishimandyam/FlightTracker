// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

/**
 * class models airplane objects to be stored in trees
 */
public class Airplane implements IAirplane, Comparable<IAirplane> {

    // instance variables
    private boolean isArrival; // true if plane is an arrival, false otherwise
    private String flightID; // String representation of a flight ID
    private String airport; // String for the Airport's name
    private String planeTime; // the ITime of the plane's arrival or departure time
    private String company; // String containing the planes company, ie American, Delta, etc


    /**
     * constructor for Airplane objects
     *
     * @param isArrival true if plane is an arrival, false otherwise
     * @param flightID  String representation of a flight ID
     * @param airport   String for the Airport's name
     * @param planeTime the ITime of the plane's arrival or departure time
     * @param company   String containing the planes company, ie American, Delta,
     *                  etc
     */
    public Airplane(boolean isArrival, String flightID, String airport, ITime planeTime, String company) {
        // assign instance variables
        this.isArrival = isArrival;
        this.flightID = flightID;
        this.airport = airport;
        this.planeTime = planeTime.toString();
        this.company = company;
    }

    /**
     * method checks if the plane is an arrival or not
     *
     * @return true if plane is an arrival, false otherwise
     */
    @Override
    public boolean getIsArrival() {
        return this.isArrival;
    }

    /**
     * method retrieves the flight's id
     *
     * @return String flightID
     */
    @Override
    public String getFlightID() {
        return this.flightID;
    }

    /**
     * method gets the airport that the plane either is going to or coming from
     *
     * @return String airport
     */
    @Override
    public String getAirport() {
        return this.airport;
    }

    /**
     * method gets the planes arrival or departure time
     *
     * @return ITime object for the time
     */
    @Override
    public ITime getTime() {
        return new Time(planeTime);
    }

    /**
     * method gets the planes arrival or departure time
     *
     * @return ITime object for the time
     */
    public String getPlaneTime() {
        return planeTime;
    }

    /**
     * method gets the planes arrival or departure time
     *
     * @return String for the time
     */
    public String getTimeAsString() {
        return this.planeTime.toString();
    }

    /**
     * method gets the planes company, ie delta, american, etc
     *
     * @return String of company
     */
    @Override
    public String getCompany() {
        return this.company;
    }

    /**
     * method compares the plane's time to the current time to see if plane has
     * arrived/departed yet
     *
     * @return true if plane is still waiting to arrive/depart, false otherwise
     */
    @Override
    public boolean isInRoute(ITime curTime) {
        return this.getTime().compareTo(curTime) > 0;
    }

    /**
     * Compares this object with the specified object for order. Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>
     * The implementor must ensure
     * {@code sgn(x.compareTo(y)) == -sgn(y.compareTo(x))}
     * for all {@code x} and {@code y}. (This
     * implies that {@code x.compareTo(y)} must throw an exception iff
     * {@code y.compareTo(x)} throws an exception.)
     *
     * <p>
     * The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>
     * Finally, the implementor must ensure that {@code x.compareTo(y)==0}
     * implies that {@code sgn(x.compareTo(z)) == sgn(y.compareTo(z))}, for
     * all {@code z}.
     *
     * <p>
     * It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}. Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact. The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * <p>
     * In the foregoing description, the notation
     * {@code sgn(}<i>expression</i>{@code )} designates the mathematical
     * <i>signum</i> function, which is defined to return one of {@code -1},
     * {@code 0}, or {@code 1} according to whether the value of
     * <i>expression</i> is negative, zero, or positive, respectively.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(IAirplane o) {
        return this.getTime().compareTo(o.getTime());
    }

    /**
     * method parses the airplane object and returns a string representation of it
     *
     * @return String representation of the airplane object
     */
    @Override
    public String toString() {
        // create string to return
        String toReturn = "";

        // set toReturn string
        toReturn = "Arrival: " + this.getIsArrival() + ", Flight ID: " + this.getFlightID() + ", Airport: " +
                this.getAirport() + ", Time: " + this.getTimeAsString() + ", Company: " + this.getCompany();

        return toReturn;
    }

}