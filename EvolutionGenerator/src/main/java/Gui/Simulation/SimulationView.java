package Gui.Simulation;

import Simulation.SimulationEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class SimulationView extends Application {

    public Thread engineThread;
    public SimulationEngine simulationEngine;
    public boolean isRunning;

    @Override
    public void init() throws Exception {
        super.init();

        simulationEngine = new SimulationEngine(null);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.show();
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
