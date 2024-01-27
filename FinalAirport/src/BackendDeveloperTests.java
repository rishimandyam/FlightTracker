// --== CS400 Project Two File Header ==--
// Name: Vishesh Dhawan
// CSL Username: dhawan
// Email: vdhawan2@wisc.edu
// Lecture #: 001 @11:00am

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class BackendDeveloperTests {

    /**
     * method tests the constructor for Airplane and ensures that all the getter methods work properly
     */
    @Test
    void test1() {
        //create a time object and an Airplane object
        Time time1 = new Time(8, 14);
        Airplane plane1 = new Airplane(true, "13243534", "KIR", time1, "Delta");

        //validate output of getIsArrival()
        Assertions.assertEquals(true, plane1.getIsArrival());

        //validate output of getFlightID()
        Assertions.assertEquals("13243534", plane1.getFlightID());

        //validate output of getAirport()
        Assertions.assertEquals("KIR", plane1.getAirport());

        //validate output of getTime()
        Assertions.assertEquals(time1, plane1.getTime());

        //validate output of getCompany()
        Assertions.assertEquals("Delta", plane1.getCompany());

    }

    /**
     * method tests the isInRoute() method of the Airplane class, the toString method of Airplane class,
     * getTimeAsString method of Airplane class, and getCurrentTime method of time class
     */
    @Test
    void test2() {
        //create a time object and an Airplane object
        Time time1 = new Time(8, 14);
        Airplane plane1 = new Airplane(true, "13243534", "KIR", time1, "Delta");

        //create a current time object
        Time curTime = new Time(4, 9);

        //validate output of isInRoute()
        Assertions.assertEquals(true, plane1.isInRoute(curTime));

        //validate output of toString()
        String correctToString = "Arrival: true, Flight ID: 13243534, Airport: KIR, Time: 8:14, Company: Delta";
        Assertions.assertEquals(correctToString, plane1.toString());

        //validate output of getTimeAsString() and getCurrentTime()
        Assertions.assertEquals("8:14", plane1.getTimeAsString());


    }

    /**
     * method tests the constructor for the Airport class
     * this involves creating the arrivalsTree, departuresTree, arrivedPlanesList, departedPlanesList,
     * and the flights arraylist
     * then, the setUpTrees method is called to properly load airplanes into their respective trees
     */
    @Test
    void test3() throws FileNotFoundException {

        //create placeholder arraylist of 5 planes
        ArrayList<IAirplane> tempPlanes = new ArrayList<>();

        //create time and airplane objects to add to the tempPlanes list
        Time time1 = new Time(8, 14);
        Airplane plane1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
        Time time2 = new Time(3, 19);
        Airplane plane2 = new Airplane(true, "0567887", "JFK", time2, "American");
        Time time3 = new Time(7, 56);
        Airplane plane3 = new Airplane(true, "0242352", "DEL", time3, "United");
        Time time4 = new Time(18, 14);
        Airplane plane4 = new Airplane(true, "2312444", "JNP", time4, "Emirates");
        Time time5 = new Time(8, 12);
        Airplane plane5 = new Airplane(true, "00765456", "KRC", time5, "Southwest");
        Time time6 = new Time(1, 1);
        Airplane plane6 = new Airplane(false, "2345656", "SJC", time1, "Delta");
        Time time7 = new Time(8, 4);
        Airplane plane7 = new Airplane(false, "5464365", "PAG", time2, "American");
        Time time8 = new Time(5, 43);
        Airplane plane8 = new Airplane(false, "2354367", "WYN", time3, "United");
        Time time9 = new Time(2, 34);
        Airplane plane9 = new Airplane(false, "79875564", "MSN", time4, "Emirates");
        Time time10 = new Time(19, 53);
        Airplane plane10 = new Airplane(false, "32645777", "RKO", time5, "Southwest");

        //add all planes to the tempPlanes ArrayList
        tempPlanes.add(plane1);
        tempPlanes.add(plane2);
        tempPlanes.add(plane3);
        tempPlanes.add(plane4);
        tempPlanes.add(plane5);
        tempPlanes.add(plane6);
        tempPlanes.add(plane7);
        tempPlanes.add(plane8);
        tempPlanes.add(plane9);
        tempPlanes.add(plane10);

        //create ITime variable
        Time curTime = new Time(0, 13);

        //call airport constructor
        Airport SFO = new Airport("SFO", "filePath", curTime);

        //manually set SFO's flights to the tempPlanes arraylist rather than using the airplaneLoader
        SFO.flights = tempPlanes;

        //manually call the setUpTrees method
        SFO.setUpTrees(tempPlanes, curTime);
    }

    /**
     * method tests the updateArrivals() helper and updateDepartures() helper called by the
     * updateArrivalsAndDepartures() method
     */
    @Test
    void test4() throws FileNotFoundException {

        //create placeholder arraylist of 5 planes
        ArrayList<IAirplane> tempPlanes = new ArrayList<>();

        //create time and airplane objects to add to the tempPlanes list
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
        Time time6 = new Time(1, 1);
        Airplane plane6 = new Airplane(false, "2345656", "SJC", time1, "Delta");
        Time time7 = new Time(8, 4);
        Airplane plane7 = new Airplane(false, "5464365", "PAG", time2, "American");
        Time time8 = new Time(5, 43);
        Airplane plane8 = new Airplane(false, "2354367", "WYN", time3, "United");
        Time time9 = new Time(2, 34);
        Airplane plane9 = new Airplane(false, "79875564", "MSN", time4, "Emirates");
        Time time10 = new Time(19, 53);
        Airplane plane10 = new Airplane(false, "32645777", "RKO", time5, "Southwest");

        //add all planes to the tempPlanes ArrayList
        tempPlanes.add(plane1);
        tempPlanes.add(plane2);
        tempPlanes.add(plane3);
        tempPlanes.add(plane4);
        tempPlanes.add(plane5);
        tempPlanes.add(plane6);
        tempPlanes.add(plane7);
        tempPlanes.add(plane8);
        tempPlanes.add(plane9);
        tempPlanes.add(plane10);

        //create ITime variable
        Time startTime = new Time(0, 13);

        //call airport constructor
        Airport SFO = new Airport("SFO", "filePath", startTime);

        //manually set SFO's flights to the tempPlanes arraylist rather than using the airplaneLoader
        SFO.flights = tempPlanes;

        //manually call the setUpTrees method
        SFO.setUpTrees(tempPlanes, startTime);

        //there is an arrival and a departure with a time of 1 hour 1 minute, so this is the time we will call
        // updateArrivalsAndDepartures() with
        //create a time variable for 1 hour 1 min
        Time curTime = new Time(1, 1);

        //call updateArrivalsAndDepartures with the curTime
        SFO.updateArrivalsAndDepartures(curTime);

        //make sure the size of both arrivals tree and departures tree is 4 now, since it was initially 5 each before
        //updateArrivalsAndDepartures was called
        Assertions.assertEquals(4, SFO.getArrivalsTree().size());
        Assertions.assertEquals(4, SFO.getDeparturesTree().size());

        //IMPLEMENT: make sure the inOrderPlanes method returns the correct arraylist

    }

    /**
     * method tests the departedPlanesList and arrivedPlanesList to make sure there are at max 3 planes in each
     */
    @Test
    void test5() throws FileNotFoundException {

        //create placeholder arraylist of 5 planes
        ArrayList<IAirplane> tempPlanes = new ArrayList<>();

        //create time and airplane objects to add to the tempPlanes list
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
        Time time6 = new Time(1, 1);
        Airplane plane6 = new Airplane(false, "2345656", "SJC", time1, "Delta");
        Time time7 = new Time(8, 4);
        Airplane plane7 = new Airplane(false, "5464365", "PAG", time2, "American");
        Time time8 = new Time(5, 43);
        Airplane plane8 = new Airplane(false, "2354367", "WYN", time3, "United");
        Time time9 = new Time(2, 34);
        Airplane plane9 = new Airplane(false, "79875564", "MSN", time4, "Emirates");
        Time time10 = new Time(19, 53);
        Airplane plane10 = new Airplane(false, "32645777", "RKO", time5, "Southwest");

        //add all planes to the tempPlanes ArrayList
        tempPlanes.add(plane1);
        tempPlanes.add(plane2);
        tempPlanes.add(plane3);
        tempPlanes.add(plane4);
        tempPlanes.add(plane5);
        tempPlanes.add(plane6);
        tempPlanes.add(plane7);
        tempPlanes.add(plane8);
        tempPlanes.add(plane9);
        tempPlanes.add(plane10);

        //create ITime variable
        Time startTime = new Time(0, 13);

        //call airport constructor
        Airport SFO = new Airport("SFO", "filePath", startTime);

        //manually set SFO's flights to the tempPlanes arraylist rather than using the airplaneLoader
        SFO.flights = tempPlanes;

        //manually call the setUpTrees method
        SFO.setUpTrees(tempPlanes, startTime);

        //there is an arrival and a departure with a time of 1 hour 1 minute, so this is the time we will call
        // updateArrivalsAndDepartures() with
        //create a time variable for 1 hour 1 min
        Time curTime = new Time(1, 1);

        //create 6 planes
        Time timex1 = new Time(8, 14);
        Airplane planex1 = new Airplane(true, "0000001", "KIR", time1, "Delta");
        Time timex2 = new Time(3, 19);
        Airplane planex2 = new Airplane(true, "0567887", "JFK", time2, "American");
        Time timex3 = new Time(1, 1);
        Airplane planex3 = new Airplane(true, "0242352", "DEL", time3, "United");
        Time timex4 = new Time(18, 14);
        Airplane planex4 = new Airplane(false, "2312444", "JNP", time4, "Emirates");
        Time timex5 = new Time(8, 12);
        Airplane planex5 = new Airplane(false, "00765456", "KRC", time5, "Southwest");
        Time timex6 = new Time(1, 1);
        Airplane planex6 = new Airplane(false, "2345656", "SJC", time1, "Delta");

        // add 3 planes each to the arrivedPlanesList and departedPlanesList
        SFO.arrivedPlanesList.add(planex1);
        SFO.arrivedPlanesList.add(planex2);
        SFO.arrivedPlanesList.add(planex3);
        SFO.departedPlanesList.add(planex4);
        SFO.departedPlanesList.add(planex5);
        SFO.departedPlanesList.add(planex6);

        //call updateArrivalsAndDepartures with the curTime, should add 1 plane each to arrivedPlanesList and
        // departedPlanesList
        SFO.updateArrivalsAndDepartures(curTime);

        //make sure arrivedPlanesList and departedPlanesList size is 3
        Assertions.assertEquals(3, SFO.getPastArrivals().size());
        Assertions.assertEquals(3, SFO.getPastDepartures().size());

    }

}