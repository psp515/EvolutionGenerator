package Gui.Simulation;

import Interfaces.IPropertyChanged;
import Models.SimulationSettings;
import Models.SimulationStatus;
import Simulation.SimulationEngine;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;

import static java.lang.System.out;

public class SimulationView implements IPropertyChanged {

    public SimulationEngine simulationEngine;
    public volatile SimulationStatus status;

    Stage mainView;
    Scene mainScene;
    GridPane map;
    GridPane statistics;
    HBox actions;
    GridPane watchedAnimal;


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
        scrollPane.setPadding(new Insets(10,10,10,10));
        mainScene = new Scene(scrollPane);
        mainView.setScene(mainScene);
        mainView.setMinWidth(800);
        mainView.setMinHeight(400);
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
        map.getChildren().clear();

        var width = simulationEngine._simulationSettings.width;
        var height = simulationEngine._simulationSettings.height;

        for (int i = 0; i < width; i++ )
            map.getColumnConstraints().add(new ColumnConstraints(  ));

        for ( int i = 0; i < height; i++ )
            map.getRowConstraints().add(new RowConstraints(  ));

        var fields = simulationEngine.getMapFields();

        for(int i =0; i < width;i++)
        {
            for(int j = 0; j < height;j++)
            {
                var field = fields[i][j];
                var fieldView = field.getView();
                map.add( fieldView.container,i, j,1,1);
            }
        }

        map.setGridLinesVisible(true);
    }

    public void drawStatistics() {
        statistics.getChildren().clear();

        var stats = simulationEngine.getMapStatistics();

        for ( int i = 0; i < 7; i++ )
            statistics.getColumnConstraints().add(new ColumnConstraints(  ));
        for ( int i = 0; i < 2; i++ )
            statistics.getRowConstraints().add(new RowConstraints(  ));

        statistics.add(new Label("Animals Count"),0,0);
        statistics.add(new Label(String.valueOf(stats.animalsOnMap)),0,1);

        statistics.add(new Label("Food Count"),1,0);
        statistics.add(new Label(String.valueOf(stats.foodOnMap)),1,1);

        statistics.add(new Label("Free Count"),2,0);
        statistics.add(new Label(String.valueOf(stats.placesFreeFromAnimalCount)),2,1);

        statistics.add(new Label("Most Popular Gens"),3,0);
        statistics.add(new Label("TODO"),3,1);

        statistics.add(new Label("Avg. Energy"),4,0);
        statistics.add(new Label(String.valueOf(stats.averageEnergy)),4,1);

        statistics.add(new Label("Avg. Live Span"),5,0);
        statistics.add(new Label(String.valueOf(stats.averageLiveLength)),5,1);


        statistics.add(new Label("Simulation Day"),6,0);
        statistics.add(new Label(String.valueOf(simulationEngine.getSimulationDay())),6,1);


        statistics.setGridLinesVisible(true);
    }

    public void drawWatchedAnimal() {

        var animal = simulationEngine.getMarkledAnimal();
        if(animal == null)
            return;

        watchedAnimal.getChildren().clear();
        watchedAnimal.setGridLinesVisible(true);

        for ( int i = 0; i < 6; i++ )
        {
            watchedAnimal.getColumnConstraints().add(new ColumnConstraints(  ));
        }
        for ( int i = 0; i < 2; i++ )
            watchedAnimal.getRowConstraints().add(new RowConstraints(  ));

        watchedAnimal.add(new Label("Genes"),0,0);
        watchedAnimal.add(new Label(Arrays.toString(animal._genotype.getGenes())),0,1);

        watchedAnimal.add(new Label("Active Gene"),1,0);
        watchedAnimal.add(new Label(String.valueOf(animal._genotype.getActiveGene())),1,1);

        watchedAnimal.add(new Label("Energy"),2,0);
        watchedAnimal.add(new Label(String.valueOf(animal.getEnergy())),2,1);

        watchedAnimal.add(new Label("Eaten Food"),3,0);
        watchedAnimal.add(new Label(String.valueOf(animal.getEatenFood())),3,1);

        watchedAnimal.add(new Label("Childrens"),4,0);
        watchedAnimal.add(new Label(String.valueOf(animal.getChildrensCount())),4,1);

       if(animal.getDeathDay()>=0){
           watchedAnimal.add(new Label("Died at"),5,0);
           watchedAnimal.add(new Label(String.valueOf(animal.getDeathDay())),5,1);
       }
       else {
           watchedAnimal.add(new Label("Lives"),5,0);
           watchedAnimal.add(new Label(String.valueOf(simulationEngine.getSimulationDay() - animal.getCreationDay())),5,1);
       }
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

        status.isRunning = true;
        Task task = new Task<Void>() {
            @Override
            public Void call() {

                while (status.isRunning)
                {
                    simulationEngine.run();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                return null;
            }
        };

        new Thread(task).start();
    }
    public void stopSimulation() {

        out.println("stop");
        status.isRunning = false;
        refreshMap();
    }
    private void selectAnimalToWatch() {
        //TODO
    }
    private void markAnimals() {
        //TODO
    }
    @Override
    public void propertyChanged() {

        Platform.runLater(this::refreshMap);

    }
}
