

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class Tests the FrontendDeveloper class along with the Airport, Airplane, AirplaneRBT, and
 * time classes
 */
public class FrontendDeveloperTests{

  /**
   * Tester method for Airport initialization
   */
  @Test
  public void test1() throws Exception {
    String airport = "ORD";
    AirplaneRBT departures = new AirplaneRBT();

    AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("1:00"), "Southwest");
    AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("1:30"), "Southwest");
    AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:40"), "Southwest");
    AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:50"), "Southwest");
    AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:55"), "Southwest");

    departures.insert(a1);
    departures.insert(a2);
    departures.insert(a3);
    departures.insert(a4);
    departures.insert(a5);

    departures.remove(a3);

    Assertions.assertEquals("[ Southwest Flight: 111111 Departing: ORD\t1:00, Southwest Flight: 111111 Departing: ORD\t1:30, Southwest Flight: 111111 Departing: ORD\t2:50, Southwest Flight: 111111 Departing: ORD\t2:55 ]", departures.toInOrderString());

  }

  /**
   * Tester method to set up trees
   */
  @Test
  public void test2() throws FileNotFoundException {
    String airport = "ORD";
    AirplaneRBT departures = new AirplaneRBT();

    AirplanePlaceholder a1 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("1:00"), "Southwest");
    AirplanePlaceholder a2 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("1:30"), "Southwest");
    AirplanePlaceholder a3 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:40"), "Southwest");
    AirplanePlaceholder a4 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:50"), "Southwest");
    AirplanePlaceholder a5 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("2:55"), "Southwest");

    departures.insert(a1);
    departures.insert(a2);
    departures.insert(a3);
    departures.insert(a4);
    departures.insert(a5);

    departures.remove(a3);

    Assertions.assertEquals("[ Southwest Flight: 111111 Departing: ORD\t1:30, Southwest Flight: 111111 Departing: ORD\t1:00, Southwest Flight: 111111 Departing: ORD\t2:50, Southwest Flight: 111111 Departing: ORD\t2:55 ]", departures.toLevelOrderString());


  }

  /**
   * Tester method to check Algorithm Engineer tests
   */
  @Test
  public void test3() throws FileNotFoundException {
    String airport = "ORD";
    AirplaneRBT departures = new AirplaneRBT();

    AirplanePlaceholder a10 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("17:45"), "Southwest");
    AirplanePlaceholder a11 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("19:21"), "Southwest");
    AirplanePlaceholder a12 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("19:31"), "Southwest");
    AirplanePlaceholder a13 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("20:49"), "Southwest");
    AirplanePlaceholder a14 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("22:54"), "Southwest");
    AirplanePlaceholder a15 = new AirplanePlaceholder(false, Integer.toString(111111), airport,
            new Time("23:02"), "Southwest");

    departures.insert(a10);
    departures.insert(a11);
    departures.insert(a12);
    departures.insert(a13);
    departures.insert(a14);
    departures.insert(a15);

    departures.remove(a11);

    Assertions.assertEquals("[ Southwest Flight: 111111 Departing: ORD\t17:45, Southwest Flight: 111111 Departing: ORD\t19:31, Southwest Flight: 111111 Departing: ORD\t20:49, Southwest Flight: 111111 Departing: ORD\t22:54, Southwest Flight: 111111 Departing: ORD\t23:2 ]", departures.toInOrderString());
    Assertions.assertEquals("[ Southwest Flight: 111111 Departing: ORD\t20:49, Southwest Flight: 111111 Departing: ORD\t17:45, Southwest Flight: 111111 Departing: ORD\t22:54, Southwest Flight: 111111 Departing: ORD\t19:31, Southwest Flight: 111111 Departing: ORD\t23:2 ]", departures.toLevelOrderString());

  }

  /**
   * Tests if getNextArrivals returns the proper arraylist
   */
  @Test
  public void test4() throws FileNotFoundException {
    String name = "JFK";
    FrontendTesterHelper airport = new FrontendTesterHelper(name);

    Time time1 = new Time(8, 14);
    Airplane plane1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
    Time time2 = new Time(3, 19);
    Airplane plane2 = new Airplane(true, "0567887", "JFK", time2, "American");
    Time time3 = new Time(1, 1);
    Airplane plane3 = new Airplane(true, "0242352", "DEL", time3, "United");
    Time time4 = new Time(18, 14);
    Airplane plane4 = new Airplane(true, "2312444", "JNP", time4, "Emirates");
    Time time5 = new Time(8, 12);
    Airplane plane5 = new Airplane(true, "00765456", "KRC", time5, "Southwest");

    ArrayList<IAirplane> planesList = new ArrayList<>();

    planesList.add(plane1);
    planesList.add(plane2);
    planesList.add(plane3);
    planesList.add(plane4);
    planesList.add(plane5);

    Assertions.assertEquals(planesList.get(1).getFlightID(), airport.getNextArrivals().get(1).getFlightID());
  }

  /**
   * Tests if getNextDepartures returns the proper arraylist
   */
  @Test
  public void test5() throws FileNotFoundException {
    String name = "JFK";
    FrontendTesterHelper airport = new FrontendTesterHelper(name);

    Time time1 = new Time(8, 14);
    Airplane plane1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
    Time time2 = new Time(3, 19);
    Airplane plane2 = new Airplane(true, "0567887", "JFK", time2, "American");
    Time time3 = new Time(1, 1);
    Airplane plane3 = new Airplane(true, "0242352", "DEL", time3, "United");
    Time time4 = new Time(18, 14);
    Airplane plane4 = new Airplane(true, "2312444", "JNP", time4, "Emirates");
    Time time5 = new Time(8, 12);
    Airplane plane5 = new Airplane(true, "00765456", "KRC", time5, "Southwest");

    ArrayList<IAirplane> planesList = new ArrayList<>();

    planesList.add(plane1);
    planesList.add(plane2);
    planesList.add(plane3);
    planesList.add(plane4);
    planesList.add(plane5);

    Assertions.assertEquals(planesList.get(1).getFlightID(), airport.getNextDepartures().get(1).getFlightID());
  }

  /**
   * tests if getPastArrivals returns the proper arraylist
   */
  @Test
  public void test6() throws FileNotFoundException {
    String name = "JFK";
    FrontendTesterHelper airport = new FrontendTesterHelper(name);

    Time time1 = new Time(8, 14);
    Airplane plane1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
    Time time2 = new Time(3, 19);
    Airplane plane2 = new Airplane(true, "0567887", "JFK", time2, "American");
    Time time3 = new Time(1, 1);
    Airplane plane3 = new Airplane(true, "0242352", "DEL", time3, "United");
    Time time4 = new Time(18, 14);
    Airplane plane4 = new Airplane(true, "2312444", "JNP", time4, "Emirates");
    Time time5 = new Time(8, 12);
    Airplane plane5 = new Airplane(true, "00765456", "KRC", time5, "Southwest");

    ArrayList<IAirplane> planesList = new ArrayList<>();

    planesList.add(plane1);
    planesList.add(plane2);
    planesList.add(plane3);
    planesList.add(plane4);
    planesList.add(plane5);

    Assertions.assertEquals(planesList.get(1).getFlightID(), airport.getPastArrivals().get(1).getFlightID());
  }

  /**
   * Tests if getPastDepartures returns the proper arraylist
   */
  @Test
  public void test7() throws FileNotFoundException {
    String name = "JFK";
    FrontendTesterHelper airport = new FrontendTesterHelper(name);

    Time time1 = new Time(8, 14);
    Airplane plane1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
    Time time2 = new Time(3, 19);
    Airplane plane2 = new Airplane(true, "0567887", "JFK", time2, "American");
    Time time3 = new Time(1, 1);
    Airplane plane3 = new Airplane(true, "0242352", "DEL", time3, "United");
    Time time4 = new Time(18, 14);
    Airplane plane4 = new Airplane(true, "2312444", "JNP", time4, "Emirates");
    Time time5 = new Time(8, 12);
    Airplane plane5 = new Airplane(true, "00765456", "KRC", time5, "Southwest");

    ArrayList<IAirplane> planesList = new ArrayList<>();

    planesList.add(plane1);
    planesList.add(plane2);
    planesList.add(plane3);
    planesList.add(plane4);
    planesList.add(plane5);

    Assertions.assertEquals(planesList.get(1).getFlightID(), airport.getPastDepartures().get(1).getFlightID());
  }

  /**
   * Checks if Airplane constructor works properly
   */
  @Test
  public void test8() {

    Time time1 = new Time(3, 1);
    Airplane plane1 = new Airplane(true, "652345", "MSN", time1, "Southwest");


    Assertions.assertTrue(plane1.getIsArrival());

    Assertions.assertEquals("652345", plane1.getFlightID());

    Assertions.assertEquals("MSN", plane1.getAirport());

    Assertions.assertEquals(time1, plane1.getTime());

    Assertions.assertEquals("Southwest", plane1.getCompany());

  }

  /**
   *
   */
  @Test
  public void test9() {
    Time time = new Time(3,5);
    time.oneMinutePassed();
    Assertions.assertEquals("3:06", time.toString());

  }

}
