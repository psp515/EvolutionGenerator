import Gui.Simulation.SimulationView;
import Models.SimulationSettings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

import static java.lang.System.out;

public class App extends Application
{
    SimulationSettings settings;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("App.fxml")));

        Button startButton = new Button("Start");
        HBox formBox = new HBox();
        formBox.getChildren().add(startButton);
        VBox vBox = new VBox();
        vBox.getChildren().add(formBox);
        primaryStage.setScene(new Scene(vBox));

        primaryStage.show();

        startButton.setOnAction( (e) -> {try{
                StartSimulationClicked();
            }
        catch (Exception z)
        {
            out.println(z);
        }});



    }

    public void StartSimulationClicked()
    {

        //TODO validate Settings
        // settings.clone()
        new SimulationView(null);

    }
}