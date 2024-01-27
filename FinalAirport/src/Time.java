// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

/**
 * class represents a Time object
 */
public class Time implements ITime {

    // private instance methods for hour and min
    private int hour;
    private int minute;

    /**
     * constructor that takes integer params
     *
     * @param hour   the hour int
     * @param minute the minute int
     */
    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * constructor that takes in a string and parses it to set the time
     *
     * @param currentTime string representation of the time Hour:Minute -> 2:13
     */
    public Time(String currentTime) {
        // split string
        String[] time = currentTime.split(":");

        // set the hour and minute
        this.hour = Integer.parseInt(time[0]);
        this.minute = Integer.parseInt(time[1]);
    }

    /**
     * method returns the current time as a string -> "2:13"
     *
     * @return
     */
    @Override
    public String toString() {
        // make sure the time is 2 digits for minute
        if (this.minute < 10) {
            if (this.hour < 12) {
                return this.hour + ":0" + this.minute;
            }
        }
        return this.hour + ":" + this.minute;
    }

    /**
     * method increments the minute, and fixes hour if needed
     */
    @Override
    public void oneMinutePassed() {
        // increment minute
        this.minute++;
        // check if minute is 60, if so, increment hour and set minute to 0
        if (this.minute == 60) {
            this.hour++;
            this.minute = 0;
            // check if hour is 24, if so, set hour to 0 and sysout "Next Day"
            if (this.hour == 24) {
                hour = 0;
                System.out.println("Next Day");
            }
        }
    }

    /**
     * method implements the compare to function
     *
     * @param time the time to compare
     * @return pos value if this > time, neg value if this < time, 0 if
     *         this.equals(time)
     */
    @Override
    public int compareTo(ITime time) {
        if (time instanceof Time) {
            if (this.hour > ((Time) time).hour) {
                return 1;
            } else if (this.hour < ((Time) time).hour) {
                return -1;
            } else if (this.hour == ((Time) time).hour) {
                if (this.minute > ((Time) time).minute) {
                    return 1;
                } else if (this.minute < ((Time) time).minute) {
                    return -1;
                }
            }
        }
        return 0;
    }
}