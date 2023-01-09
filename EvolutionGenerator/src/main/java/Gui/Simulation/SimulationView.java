package Gui.Simulation;

import Elements.Animal;
import Interfaces.IPropertyChanged;
import Models.SimulationSettings;
import Models.SimulationStatus;
import Simulation.SimulationEngine;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;
import java.util.HashMap;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

// TODO Selecting One Animal - > to mozna zrobić combo boxem i wybór jednego
// TODO Kopulacja nie działa poprawnie

public class SimulationView implements IPropertyChanged {

    public SimulationEngine simulationEngine;
    public volatile SimulationStatus status;
    long miliseconds;

    Stage mainView;
    Scene mainScene;
    GridPane map;
    GridPane statistics;
    HBox actions;
    GridPane watchedAnimal;

    ComboBox animalComboBox;
    ObservableList<String> animalsOptions;
    HashMap<Integer, Animal> animalMapper;

    boolean genotypesMarked;

    public SimulationView(SimulationSettings settings) {

        genotypesMarked = false;

        map = new GridPane();
        statistics = new GridPane();
        actions = new HBox();
        watchedAnimal = new GridPane();

        status = new SimulationStatus();
        status.isRunning = false;
        status.isFinished = true;

        simulationEngine = new SimulationEngine(settings, status,this);

        drawStatistics();
        drawWatchedAnimal();
        drawMap();
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
        mainView.setMinHeight(800);
        mainView.setTitle("Simulation");
        mainView.show();

        mainView.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                status.isRunning = false;
            }
        });

    }

    //region DRAW

    public Label makeLabel(String text)
    {
        var label = new Label(text);
        label.setPadding(new Insets(4,4,4,4));
        label.setAlignment(Pos.CENTER);
        return label;
    }

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

        map.setGridLinesVisible(false);
        map.setGridLinesVisible(true);
    }

    public void drawStatistics() {

        statistics.getChildren().clear();

        var stats = simulationEngine.getMapStatistics();

        for ( int i = 0; i < 7; i++ )
            statistics.getColumnConstraints().add(new ColumnConstraints(  ));
        for ( int i = 0; i < 2; i++ )
            statistics.getRowConstraints().add(new RowConstraints(  ));

        statistics.add(makeLabel("Animals Count"),0,0);
        statistics.add(makeLabel(String.valueOf(stats.animalsOnMap)),0,1);

        statistics.add(makeLabel("Food Count"),1,0);
        statistics.add(makeLabel(String.valueOf(stats.foodOnMap)),1,1);

        statistics.add(makeLabel("Free Count"),2,0);
        statistics.add(makeLabel(String.valueOf(stats.placesFreeFromAnimalCount)),2,1);

        statistics.add(makeLabel("Most Popular Gens"),3,0);
        statistics.add(makeLabel(stats.mostPoupularGenes),3,1);

        statistics.add(makeLabel("Avg. Energy"),4,0);
        statistics.add(makeLabel(String.valueOf(stats.averageEnergy)),4,1);

        statistics.add(makeLabel("Avg. Live Span"),5,0);
        statistics.add(makeLabel(String.valueOf(stats.averageLiveLength)),5,1);


        statistics.add(makeLabel("Simulation Day"),6,0);
        statistics.add(makeLabel(String.valueOf(simulationEngine.getSimulationDay())),6,1);

        statistics.setGridLinesVisible(false);
        statistics.setGridLinesVisible(true);
    }

    public void drawWatchedAnimal() {
        watchedAnimal.getChildren().clear();

        var animal = simulationEngine.getWatchedAnimal();
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

        watchedAnimal.add(makeLabel("Genes"),0,0);
        watchedAnimal.add(makeLabel(Arrays.toString(animal._genotype.getGenes())),0,1);


        watchedAnimal.add(makeLabel("Active Gene"),1,0);
        watchedAnimal.add(makeLabel(String.valueOf(animal._genotype.getActiveGene())),1,1);


        watchedAnimal.add(makeLabel("Energy"),2,0);
        watchedAnimal.add(makeLabel(String.valueOf(animal.getEnergy())),2,1);


        watchedAnimal.add(makeLabel("Eaten Food"),3,0);
        watchedAnimal.add(makeLabel(String.valueOf(animal.getEatenFood())),3,1);

        watchedAnimal.add(makeLabel("Children Count"),4,0);
        watchedAnimal.add(makeLabel(String.valueOf(animal.getChildrensCount())),4,1);

       if(animal.getDeathDay()>=0){
           watchedAnimal.add(makeLabel("Died At"),5,0);
           watchedAnimal.add(makeLabel(String.valueOf(animal.getDeathDay())),5,1);
       }
       else {
           watchedAnimal.add(makeLabel("Is Living"),5,0);
           watchedAnimal.add(makeLabel(String.valueOf(simulationEngine.getSimulationDay() - animal.getCreationDay())),5,1);
       }

        watchedAnimal.setGridLinesVisible(false);
        watchedAnimal.setGridLinesVisible(true);
    }

    private void drawActions() {

        actions.getChildren().clear();

        actions.setSpacing(20);

        if(!status.notReadyToStartAction())
        {
            Button startButton = new Button("Start Simulation");
            actions.getChildren().add(startButton);

            Button markingButton = new Button("Mark / Unmark Most Popular Genotype (Blue)");
            actions.getChildren().add(markingButton);

            Button clearAnimalComboBoxButton = new Button("Clear watched animal");

            var animals = simulationEngine.getAnimals();
            animalMapper = new HashMap<>();
            animalsOptions = FXCollections.observableArrayList();

            for(int i =0;i<animals.size();i++)
            {
                var animal = animals.get(i);
                animalMapper.put(i, animal);
                animalsOptions.add(String.format("%04d", i) + " " + animal.getPosition() + " " + animal.getEnergy());
            }

            animalComboBox = new ComboBox();
            animalComboBox.setPromptText("Select Animal To Watch");
            animalComboBox.getItems().addAll(animalsOptions);

            actions.getChildren().add(clearAnimalComboBoxButton);
            actions.getChildren().add(animalComboBox);

            clearAnimalComboBoxButton.setOnAction((e)->{

                animalComboBox.setValue(null);

                var watched = simulationEngine.getWatchedAnimal();

                if (watched != null)
                    watched.isHighlighted = false;

                simulationEngine.setWatchedAnimal(null);
                Platform.runLater(this::refreshMap);
            });

            markingButton.setOnAction((e)->{
                markAnimalsWithGenotype();
            });

            startButton.setOnAction( (e) -> {
                startSimulation();
            });
        }
        else
        {
            Button stopButton = new Button("Stop Simulation");

            actions.getChildren().add(stopButton);

            stopButton.setOnAction( (e) -> {
                stopSimulation();
            });
        }
    }

    // endregion

    //ACTIONS

    public void startSimulation() {
        var current = currentTimeMillis();
        //prevents double click
        if(current - miliseconds < 1000 )
            return;

        if(simulationEngine.getMapStatistics().animalsOnMap <= 0)
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("There are no animals on map, simulation is ended.");
            a.show();
            return;
        }

        if(status.notReadyToStartAction())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("There is ongoing simulation or previous simulation didn't finished.");
            a.show();
            return;
        }

        if(genotypesMarked)
        {
            genotypesMarked = false;
            simulationEngine.UnMarkMostPopularGenotype();
        }

        var watched = simulationEngine.getWatchedAnimal();

        if (watched != null)
            watched.isHighlighted = false;

        if(animalComboBox.getValue() != null)
        {
            String value = (String) animalComboBox.getValue();
            out.println(value);
            int key = Integer.parseInt(value.substring(0,4));
            watched = animalMapper.get(key);
            watched.isHighlighted = true;
            simulationEngine.setWatchedAnimal(watched);
        }
        else {
            simulationEngine.setWatchedAnimal(null);
        }

        status.isRunning = true;
        drawActions();

        Task task = new Task<Void>() {
            @Override
            public Void call() {

                status.isFinished= false;
                while (status.isRunning)
                {
                    if(simulationEngine.getMapStatistics().animalsOnMap > 0)
                    {
                        simulationEngine.run();
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else {
                        status.isRunning = false;
                        break;
                    }
                }
                status.isFinished= true;

                return null;
            }
        };

        new Thread(task).start();
    }
    public void stopSimulation()  {

        status.isRunning = false;

        miliseconds = currentTimeMillis();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        refreshMap();
        drawActions();
    }

    private void markAnimalsWithGenotype()
    {
        if(status.notReadyToStartAction())
        {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("There is ongoing simulation or previous simulation didn't finished. Please wait or end simulation.");
            a.show();
            return;
        }

        if(genotypesMarked)
        {
            genotypesMarked = false;
            simulationEngine.UnMarkMostPopularGenotype();
            refreshMap();
        }
        else {
            genotypesMarked = true;
            simulationEngine.MarkMostPopularGenotype();
            refreshMap();
        }
    }

    @Override
    public void propertyChanged()
    {
        Platform.runLater(this::refreshMap);
    }
}
