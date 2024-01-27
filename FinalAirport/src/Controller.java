// --== CS400 Project Two File Header ==--
// Name: Rishi Mandyamm
// CSL Username: mandyam
// Email: rmandyam@wisc.edu
// Lecture #: 001 @11:00am

//package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements IAirportFrontend {

    @FXML
    private Label myLabel;

    @FXML
    private TextField myTextField;

    @FXML
    private Button myButton;

    @FXML
    private Text timer;

    @FXML
    private Label airportNameLabel;

    @FXML
    private Label arrivalsLabel;

    @FXML
    private Label departuresLabel;

    Time time = new Time(0, 0);

    IAirport airport;

    // GenerateData generateData = new GenerateData();

    private Stage stage;
    private Scene scene;
    private Parent root;

    private String airportName;

    public Controller() throws FileNotFoundException {
    }

    /**
     * submits airport name entered
     *
     * @param event
     * @throws IOException
     */
    public void submit(ActionEvent event) throws IOException {
        airportName = myTextField.getText();
        // generateData.createData();
        // String filePath = generateData.getpath();
        // Airport airport = new Airport(airportName, filePath, time);
        switchToDisplayScene(event);
    }

    /**
     * Switches from airport select window to display window
     *
     * @param event
     * @throws IOException
     */
    public void switchToDisplayScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DisplayWindow.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Starts the timer and shows the flights arriving and departing
     *
     * @param event
     * @throws IOException
     */
    public void startModel(ActionEvent event) throws IOException {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            time.oneMinutePassed();
                            timer.setText(time.toString());
                            airport.updateArrivalsAndDepartures(time);
                            displayBoard(time);
                        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    /**
     * Displays the next 10 arrivals and next 10 departures
     *
     * @param time current time
     */
    public void displayBoard(ITime time) {
        ArrayList<IAirplane> arrivalsList = airport.getNextArrivals();
        String arrivalsListText = "";
        for (int i = 0; i < arrivalsList.size(); i++) {
            arrivalsListText = "Flight: " + arrivalsList.get(i).getFlightID() +
                    " From: " + arrivalsList.get(i).getAirport() +
                    " At: " + arrivalsList.get(i).getTime().toString() + "\n";

            arrivalsListText += arrivalsListText;

        }
        arrivalsLabel.setText(arrivalsListText);

        ArrayList<IAirplane> departuresList = airport.getNextDepartures();
        String departuresListText = "";
        for (int i = 0; i < departuresList.size(); i++) {
            departuresListText = "Flight: " + departuresList.get(i).getFlightID() +
                    " From: " + departuresList.get(i).getAirport() +
                    " At: " + departuresList.get(i).getTime().toString() + "\n";

            departuresListText += departuresListText;

        }
        departuresLabel.setText(departuresListText);
    }

    @Override
    public void runCommandLoop() {
    }

    @Override
    public void displayCommandMenu() {
    }

    @Override
    public void displayFlights() {
    }

    @Override
    public void searchByTimeRange(String startTime, String endTime) {
    }
}
