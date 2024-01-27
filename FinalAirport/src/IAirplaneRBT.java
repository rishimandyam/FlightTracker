import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalTime;

/**
 * This class is implemented by a RBT that stores a tree of values that is
 * confined to the 3 rules of a RBT, according to the compareTo() define within
 * T.
 */
public interface IAirplaneRBT<T extends Comparable<T>> extends SortedCollectionInterface<T> {

    /**
     * This remove method remove the specified airplane from the RBT and adjust the
     * tree to conform to the rules of a RBT. It is called when a plane departs from
     * the airport.
     * 
     * @param data the specified airplane to remove
     * @return the removed node
     * @throws NoSuchElementException if the element does not exist in the RBT
     */
    public IAirplane remove(T data) throws NoSuchElementException;

    /**
     * This get method gets an airplane that departs at a certain time.
     * 
     * @param time the time in 24hr format which we are looking for an airplane for
     * @return a Airplane that takes off at the specified time
     * @throws NoSuchElementException if there is no airplane that departs at that
     *                                time
     */
    public IAirplane getByTime(ITime time) throws NoSuchElementException;

    /**
     * Returns a array list of airplanes in a tree instead of a String-
     * inOrderTraversal
     * 
     * @return a list of Airplanes
     */
    public ArrayList<IAirplane> inOrderPlanes();

    /**
     * This method takes two time inputs and searches for any airplanes that
     * depart
     * during this time period.
     *
     * @param t1 the low-bound time period
     * @param t2 the high-bound time period
     * @return a list of airplanes that depart during this time period
     * @throws IllegalArgumentException when t1 is after t2
     */
    public List<IAirplane> searchByPeriod(ITime t1, ITime t2) throws IllegalArgumentException;
}
