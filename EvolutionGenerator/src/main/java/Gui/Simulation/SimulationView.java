package Gui.Simulation;

import Interfaces.IPropertyChanged;
import Models.SimulationRun;
import Models.SimulationSettings;
import Simulation.SimulationEngine;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.System.out;

public class SimulationView implements IPropertyChanged, Runnable {

    public Thread engineThread;
    public SimulationEngine simulationEngine;
    public volatile SimulationRun isRunning;

    Label label;

    public SimulationView(SimulationSettings settings)
    {
        Stage s= new Stage();
        Button startButton = new Button("Start");
        Button endButton = new Button("end");
        label = new Label("0");
        HBox formBox = new HBox();
        formBox.getChildren().add(startButton);
        formBox.getChildren().add(endButton);
        formBox.getChildren().add(label);
        VBox vBox = new VBox();
        vBox.getChildren().add(formBox);
        s.setScene(new Scene(vBox));
        s.show();

        startButton.setOnAction( (e) -> {
            startSimulation();
        });


        endButton.setOnAction( (e) -> {
            stopSimulation();
        });
        isRunning = new SimulationRun();
        isRunning.isRunning = false;


        simulationEngine = new SimulationEngine(null, isRunning,this);

    }


    public void startSimulation()
    {
        if(isRunning.isRunning)
            return;

        isRunning.isRunning = true;
        var thread = new Thread(simulationEngine);
        thread.run();
    }

    public void stopSimulation()
    {
        isRunning.isRunning = false;
    }

    @Override
    public void propertyChanged() {

        Platform.runLater(() -> {
            label.setText(String.format("%d", simulationEngine.simulationDay));
            out.println(simulationEngine.simulationDay);

        });

    }

    @Override
    public void run() {
        while (true)
        {
            if(isRunning.isRunning)
                simulationEngine.run();
        }
    }
}
