import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.RadialGradient;
import javafx.stage.Stage;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayShrey extends Application {

    private ITime airportTime = new Time(0, 0);
    private Label timer = new Label();
    private Airport airport;
    private TableView<IAirplane> flightResultsTable = new TableView();

    @Override
    public void start(final Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 650);

        RadialGradient shadePaint = new RadialGradient(
                0, 1, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(1, Color.DEEPSKYBLUE),
                new Stop(0, Color.GHOSTWHITE));

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                shadePaint, null, new Insets(-5))));
        // root.setBackground(new Background(new BackgroundFill(Color.BLACK, null,
        // null)));

        // Creating title on title screen
        Label title = new Label("Airplane Display Simulator");
        VBox titleScreen = new VBox(title);
        titleScreen.setAlignment(Pos.CENTER);
        title.setStyle("-fx-font-size: 60");
        title.setTextFill(Color.BLACK);
        root.getChildren().addAll(titleScreen);

        // Creating text field for use to search for an airport
        HBox airportReciever = new HBox();
        Label flightSearch = new Label("What airport would you like to see data for?");
        TextField airportTextField = new TextField("Ex: JFK");

        flightSearch.setStyle("-fx-font-size: 20");
        airportReciever.setSpacing(20);
        titleScreen.getChildren().addAll(airportReciever);
        airportReciever.getChildren().addAll(flightSearch, airportTextField);
        airportReciever.setAlignment(Pos.CENTER);

        // Setting an onAction attribute to flight search: We will create a new scene
        // with the same background and creating an airport/generating data
        airportTextField.setOnAction(event -> {
            try {
                airport = new Airport(airportTextField.getText(),
                        "C:\\Users\\rishi\\IdeaProjects\\FinalAirport", airportTime);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            switchToAirportWindow(stage);
        });

        // Making buttons for searching for flights by a certain period and

        stage.setScene(scene);
        stage.show();

    }

    /**
     * Switches from airport select window to display window
     *
     * @param stage
     */
    public void switchToAirportWindow(final Stage stage) {
        // create a new StackPane and scene for the airport window
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 650);

        // set scene background
        RadialGradient shadePaint = new RadialGradient(
                0, 1, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(1, Color.DEEPSKYBLUE),
                new Stop(0, Color.GHOSTWHITE));

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                shadePaint, null, new Insets(-5))));

        // make vbox for all elements
        VBox vbox = new VBox();
        // Making hBox containing currentTime, Title, and Find My Flight button
        HBox titleHBox = new HBox();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            airportTime.oneMinutePassed();
                            timer.setText(airportTime.toString());
                        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Label airportLabel = new Label("Welcome to " + airport.getName() + " Airport!");
        Button findFlightButton = new Button("Find a Flight");

        findFlightButton.setOnMouseClicked(event -> {
            switchToFlightSearchWindow(stage);

        });

        titleHBox.getChildren().addAll(timer, airportLabel, findFlightButton);
        titleHBox.setAlignment(Pos.CENTER);
        titleHBox.setSpacing(150);

        // Making Label for Arrivals table
        Label arrivalLabel = new Label("Arrivals");

        // Making table for Arrivals
        TableView<IAirplane> arrivalsTable = new TableView();
        TableColumn arrFlightIDCol = new TableColumn("Flight ID");
        arrFlightIDCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        TableColumn arrTimeCol = new TableColumn("Arrival Time");
        arrTimeCol.setCellValueFactory(new PropertyValueFactory<>("planeTime"));
        TableColumn arrAirportCol = new TableColumn("Arriving From");
        arrAirportCol.setCellValueFactory(new PropertyValueFactory<>("airport"));
        TableColumn arrCompanyCol = new TableColumn("Company");
        arrCompanyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        TableColumn<IAirplane, String> arrFlightStatusCol = new TableColumn("Flight Status");
        arrFlightStatusCol.setCellValueFactory(cellData -> {
            if (!cellData.getValue().isInRoute(airportTime)) {
                return new ReadOnlyStringWrapper("Arrived");
            }
            return new ReadOnlyStringWrapper("In Route");
        });
        arrivalsTable.getColumns().addAll(arrFlightIDCol, arrTimeCol, arrAirportCol, arrCompanyCol, arrFlightStatusCol);

        // Making label for Departures
        Label departureLabel = new Label("Departures");

        // Making table for Departures
        TableView<IAirplane> departuresTable = new TableView();
        TableColumn depFlightIDCol = new TableColumn("Flight ID");
        depFlightIDCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        TableColumn depTimeCol = new TableColumn("Departure Time");
        depTimeCol.setCellValueFactory(new PropertyValueFactory<>("planeTime"));
        TableColumn depAirportCol = new TableColumn("Departing From");
        depAirportCol.setCellValueFactory(new PropertyValueFactory<>("airport"));
        TableColumn depCompanyCol = new TableColumn("Company");
        depCompanyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        TableColumn<IAirplane, String> depFlightStatusCol = new TableColumn("Flight Status");
        depFlightStatusCol.setCellValueFactory(cellData -> {
            if (!cellData.getValue().isInRoute(airportTime)) {
                return new ReadOnlyStringWrapper("Departed");
            }
            return new ReadOnlyStringWrapper("In Route");
        });
        departuresTable.getColumns().addAll(depFlightIDCol, depTimeCol, depAirportCol, depCompanyCol, depFlightStatusCol);

        // Updating the table for Arrivals
        Timeline timeline2 = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            updateTables(arrivalsTable, departuresTable, airportTime);
                        }));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();

        // Making the exit button
        Button exitButton = new Button("Quit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        // add all elements to vbox
        vbox.getChildren().addAll(titleHBox, arrivalLabel, arrivalsTable, departureLabel, departuresTable, exitButton);
        // set stage and show
        root.getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    private void switchToFlightSearchWindow(final Stage stage) {
        // create a new StackPane and scene for the airport window
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1000, 650);

        // set scene background
        RadialGradient shadePaint = new RadialGradient(
                0, 1, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(1, Color.DEEPSKYBLUE),
                new Stop(0, Color.GHOSTWHITE));

        root.setBackground(
                new Background(
                        new BackgroundFill(
                                shadePaint, null, new Insets(-5))));

        // Creating title on flight search screen
        Label prompt = new Label("Please Enter a Window of Time (24-hour Format)");
        VBox flightSearchScreen = new VBox(prompt);
        flightSearchScreen.setAlignment(Pos.CENTER);
        prompt.setStyle("-fx-font-size: 40");
        prompt.setTextFill(Color.BLACK);
        root.getChildren().addAll(flightSearchScreen);

        // Creating text field for use to search for an airport
        HBox timeReceiver = new HBox();
        HBox timeReceiverStart = new HBox();

        Label startTimeLabel = new Label("Start Time: ");
        TextField startTime = new TextField("22:12");

        HBox timeReceiverEnd = new HBox();

        Label endTimeLabel = new Label("End Time: ");
        TextField endTime = new TextField("23:43");

        startTimeLabel.setStyle("-fx-font-size: 20");
        endTimeLabel.setStyle("-fx-font-size: 20");
        timeReceiverStart.setSpacing(20);
        timeReceiverEnd.setSpacing(20);
        timeReceiver.setSpacing(200);
        timeReceiverStart.getChildren().addAll(startTimeLabel, startTime);
        timeReceiverEnd.getChildren().addAll(endTimeLabel, endTime);
        timeReceiver.getChildren().addAll(timeReceiverStart, timeReceiverEnd);
        flightSearchScreen.getChildren().addAll(timeReceiver);
        timeReceiver.setAlignment(Pos.CENTER);

        // Setting an onAction attribute to flight search: We will create a new scene
        // with the same background and creating an airport/generating data
        flightResultsTable = new TableView();
        TableColumn arrFlightIDCol = new TableColumn("Flight ID");
        arrFlightIDCol.setCellValueFactory(new PropertyValueFactory<>("flightID"));
        TableColumn arrTimeCol = new TableColumn("Arrival Time");
        arrTimeCol.setCellValueFactory(new PropertyValueFactory<>("planeTime"));
        TableColumn arrAirportCol = new TableColumn("Arriving From");
        arrAirportCol.setCellValueFactory(new PropertyValueFactory<>("airport"));
        TableColumn arrCompanyCol = new TableColumn("Company");
        arrCompanyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
        flightResultsTable.getColumns().addAll(arrFlightIDCol, arrTimeCol, arrAirportCol, arrCompanyCol);

        // Making the exit button
        Button exitButton = new Button("Quit");
        exitButton.setAlignment(Pos.BOTTOM_RIGHT);
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        flightSearchScreen.getChildren().addAll(exitButton);

        endTime.setOnAction(event -> {
            flightResultsTable.getItems().clear();
            Label exception = new Label();
            try {
                flightResultsTable.getItems().addAll(airport.searchByPeriod(new Time(startTime.getText()), new Time(endTime.getText())));
                if (!flightSearchScreen.getChildren().contains(flightResultsTable)) {
                    flightSearchScreen.getChildren().addAll(flightResultsTable);
                }
                if (flightSearchScreen.getChildren().contains(exception)) {
                    flightSearchScreen.getChildren().remove(exception);
                }
                if (flightSearchScreen.getChildren().contains(exitButton)) {
                    exitButton.setAlignment(Pos.BOTTOM_RIGHT);
                    flightSearchScreen.getChildren().remove(exitButton);
                }
            } catch (IllegalArgumentException e) {
                exception = new Label(e.getMessage());
                exception.setStyle("-fx-font-size: 20");
                flightSearchScreen.getChildren().addAll(exception);
            }

        });



        stage.setScene(scene);
        stage.show();
    }

    private void updateTables(TableView<IAirplane> arrivalsTable, TableView<IAirplane> departuresTable, ITime curTime) {
        airport.updateArrivalsAndDepartures(curTime);

        ArrayList<IAirplane> pastArrivals = airport.getPastArrivals();
        ArrayList<IAirplane> pastDepartures = airport.getPastDepartures();

        ArrayList<IAirplane> nextArrivals = airport.getNextArrivals();
        ArrayList<IAirplane> nextDepartures = airport.getNextDepartures();
        arrivalsTable.getItems().clear();
        arrivalsTable.getItems().addAll(pastArrivals);
        arrivalsTable.getItems().addAll(nextArrivals);

        departuresTable.getItems().clear();
        departuresTable.getItems().addAll(pastDepartures);
        departuresTable.getItems().addAll(nextDepartures);
    }


    public static void main(String[] args) {
        Application.launch();
    }
}
