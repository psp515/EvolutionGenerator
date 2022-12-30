import Models.SimulationSettings;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    SimulationSettings settings;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("DashFX.fxml"));
        primaryStage.show();
        //TODO load base data
    }

    public void StartSimulationClicked()
    {

        //TODO validate Settings

        SimulationSettings copy = settings.clone();


    }
}