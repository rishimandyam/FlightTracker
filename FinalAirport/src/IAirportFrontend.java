import java.util.List;

/**
 * Instances of classes that implement this interface can be used to drive a
 * console-based text user interface for the ShowSearcher App.
 */
public interface IAirportFrontend {

  // constuctor args (IAirport) reads input from system.in

  /**
   * This method drives the entire read, eval, print loop (repl) for the
   * Airport App. This loop will continue to run until the user
   * explicitly enters the quit command.
   */
  void runCommandLoop();

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  public void displayCommandMenu(); // prints command options to System.out

  public void displayFlights(); // prints display board for the given time

  public void searchByTimeRange(String startTime, String endTime); // prints display board for time range

}
