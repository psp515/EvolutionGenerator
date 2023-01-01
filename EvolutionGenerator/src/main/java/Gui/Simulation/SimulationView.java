package Gui.Simulation;

import Models.SimulationSettings;
import Simulation.SimulationEngine;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimulationView {

    public Thread engineThread;
    public SimulationEngine simulationEngine;
    public boolean isRunning;

    public SimulationView(SimulationSettings settings)
    {
        Stage s= new Stage();
        Button startButton = new Button("Start");
        HBox formBox = new HBox();
        formBox.getChildren().add(startButton);
        VBox vBox = new VBox();
        vBox.getChildren().add(formBox);
        s.setScene(new Scene(vBox));
        s.show();
    }


    public void startSimulation()
    {
        if(isRunning)
            return;

        engineThread = new Thread(simulationEngine);
    }

    public void  stopSimulation()
    {
        isRunning = false;
    }
}
