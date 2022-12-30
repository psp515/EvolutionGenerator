import Gui.Simulation.SimulationView;
import Models.SimulationSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application
{
    SimulationSettings settings;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("App.fxml")));
        primaryStage.show();
        //TODO load base data
    }

    public void StartSimulationClicked()
    {

        //TODO validate Settings

        SimulationSettings copy = settings.clone();

        Application.launch(SimulationView.class, "");


    }
}