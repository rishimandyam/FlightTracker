// --== CS400 Project Two File Header ==--
// Name: Rishi Mandyam
// CSL Username: Mandyam
// Email: rmandyam@wisc.edu
// Lecture #: 001 @11:00am

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * placeholder class for Airport to test Frontend
 */
public class FrontendTesterHelper implements IAirport{

  String name;

  public FrontendTesterHelper(String name) throws FileNotFoundException {

    // set name
    this.name = name;

  }

  @Override
  public void addArrival(IAirplane plane) {

  }

  @Override
  public void addDeparture(IAirplane plane) {

  }

  @Override
  public void removeArrival(IAirplane plane) {

  }

  @Override
  public void removeDeparture(IAirplane plane) {

  }

  @Override
  public void updateArrivalsAndDepartures(ITime curTime) {

  }

  @Override
  public IAirplaneRBT getArrivalsTree() {
    return null;
  }

  @Override
  public IAirplaneRBT getDeparturesTree() {
    return null;
  }

  @Override
  public void setUpTrees(ArrayList<IAirplane> flights, ITime curTime) {

  }

  @Override
  public ArrayList<IAirplane> getPastArrivals() {
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

    return planesList;
  }

  @Override
  public ArrayList<IAirplane> getPastDepartures() {
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

    return planesList;
  }

  @Override
  public ArrayList<IAirplane> getNextArrivals() {
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

    return planesList;
  }

  @Override
  public ArrayList<IAirplane> getNextDepartures() {
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

    return planesList;
  }
}
