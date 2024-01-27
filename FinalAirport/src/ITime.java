// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

/**
 * this interface models time objects
 */
public interface ITime extends Comparable<ITime> {

    /**
     * this method returns the current time of the time object as a string
     *
     * @return string of time
     */
    public String toString();

    /**
     * increments the minute and fixes hour if needed
     */
    public void oneMinutePassed();

}
