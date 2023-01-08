package Gui.Simulation;

import Interfaces.IPropertyChanged;
import Models.SimulationStatus;
import Models.SimulationSettings;
import Simulation.SimulationEngine;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SimulationView implements IPropertyChanged {

    public SimulationEngine simulationEngine;
    public volatile SimulationStatus status;

    Stage mainView;
    Scene mainScene;
    GridPane map;
    GridPane statistics;
    HBox actions;
    GridPane watchedAnimal;

    Label label;

    public SimulationView(SimulationSettings settings) {

        map = new GridPane();
        statistics = new GridPane();
        actions = new HBox();
        watchedAnimal = new GridPane();

        status = new SimulationStatus();
        status.isRunning = false;

        simulationEngine = new SimulationEngine(settings, status,this);
        Platform.runLater(this::refreshMap);
        drawActions();

        mainView = new Stage();
        VBox vertical = new VBox();
        vertical.getChildren().add(actions);
        vertical.getChildren().add(statistics);
        vertical.getChildren().add(watchedAnimal);
        vertical.getChildren().add(map);
        ScrollPane scrollPane = new ScrollPane(vertical);
        mainScene = new Scene(scrollPane);
        mainView.setScene(mainScene);
        mainView.setFullScreen(true);
        mainView.show();

        mainView.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                status.isRunning = false;
            }
        });

    }

    //DRAW

    public void refreshMap(){
        drawStatistics();
        drawWatchedAnimal();
        drawMap();
    }

    public void drawMap(){

    }

    public void drawStatistics() {

    }

    public void drawWatchedAnimal() {
        if(simulationEngine.getMarkledAnimal() == null)
            return;

        watchedAnimal.getChildren().clear();
        watchedAnimal.setGridLinesVisible(true);

        int max = 7;
        //TUTAJ TRZEBA ODCZYTAC GENOM BEZ ZMIANY AKTYWOWANEGO GENU


    }

    private void drawActions() {

        Button startButton = new Button("Start Simulation");
        Button stopButton = new Button("Stop Simulation");
        Button animalButton = new Button("Select Animal");
        Button markingButton = new Button("Mark Genotype");

        actions.getChildren().add(startButton);
        actions.getChildren().add(stopButton);
        actions.getChildren().add(animalButton);
        actions.getChildren().add(markingButton);

        actions.setSpacing(20);
        actions.setPadding(new Insets(5,5,5,5));

        startButton.setOnAction( (e) -> {
            startSimulation();
        });

        stopButton.setOnAction( (e) -> {
            stopSimulation();
        });

        animalButton.setOnAction((e)->{
            selectAnimalToWatch();
        });

        animalButton.setOnAction((e)->{
            markAnimals();
        });
    }


    //ACTIONS

    public void startSimulation() {
        if(status.isRunning)
            return;

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        status.isRunning = true;
        var thread = new Thread(simulationEngine);
        thread.start();
    }
    public void stopSimulation() {
        status.isRunning = false;
    }
    private void selectAnimalToWatch() {
        //TODO
    }
    private void markAnimals() {
        //TODO
    }
    @Override
    public void propertyChanged() {
        try{
            Thread.sleep(100);
            Platform.runLater(this::refreshMap);
        } catch (InterruptedException e) {
        }
    }
}
