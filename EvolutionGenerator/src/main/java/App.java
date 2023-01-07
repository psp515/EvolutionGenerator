import Gui.Others.NumberTextField;
import Gui.Simulation.SimulationView;
import Models.SettingsWrapper;
import Models.SimulationSettings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static java.lang.System.out;

public class App extends Application
{
    SimulationSettings selectedPresetSettings;

    ObservableList<String> optionalSettings = FXCollections.observableArrayList("");



    @Override
    public void start(Stage primaryStage) throws Exception {

        selectedPresetSettings = null;
        //load Settings

        primaryStage.setScene(new Scene(PresetForm()));
        primaryStage.setWidth(500);
        primaryStage.show();

    }

    //Custom
    public VBox CustomForm()
    {
        VBox form = new VBox();
        form.setPadding(new Insets(20,20,20,20));
        form.setMinWidth(200);
        form.setMinWidth(400);

        var firstTitle = new Label("Load settings from preset");
        firstTitle.setStyle("-fx-font-weight: bold");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(

        );
        comboBox.setPromptText("Option");

        Button startButton = new Button("Start Preset Simulation");


        var secondTitle = new Label("Simulation Settings");
        secondTitle.setStyle("-fx-font-weight: bold");

        Button startButton2 = new Button("Start Simulation");

        NumberTextField integerField = new NumberTextField();
        integerField.setText(String.valueOf(selectedPresetSettings.width));



        //Adding To View

        form.getChildren().add(firstTitle);
        form.getChildren().add(comboBox);

        form.getChildren().add(secondTitle);
        form.getChildren().add(integerField);
        form.getChildren().add(startButton);

        // Actions
        startButton.setOnAction( (e) ->
        {
            var x = new SimulationSettings();
            x.width = integerField.getValue();
            StartSimulationClicked(x);
        });

        return form;
    }
    public void StartSimulationClicked(SimulationSettings settings) {

        if(!isSettingsValid())
            return;

        //TODO validate Settings
        // settings.clone()
        //new SimulationView(null);

        out.println(settings.width);

    }

    private boolean isSettingsValid() {
        return true;
    }

    // Preset
    public VBox PresetForm()
    {
        VBox form = new VBox();
        form.setPadding(new Insets(20,20,20,20));
        form.setMinWidth(200);
        form.setMinWidth(400);

        var firstTitle = new Label("Load settings from preset");
        firstTitle.setStyle("-fx-font-weight: bold");

        ComboBox comboBox = new ComboBox();
        comboBox.setPromptText("Presets");

        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent e)
                    {
                        //TODO
                        selectedPresetSettings = optionalSetting.stream().findFirst();
                        selected.setText(comboBox.getValue() + " selected");
                    }
                };

        Button startButton = new Button("Start Preset Simulation");



        //Adding To View

        form.getChildren().add(firstTitle);
        form.getChildren().add(comboBox);
        form.getChildren().add(startButton);

        // Actions
        startButton.setOnAction( (e) ->
        {
            StartPresetSimulation();
        });

        return form;
    }


    public void StartPresetSimulation()
    {
        if(selectedPresetSettings == null)
            return;

        new SimulationView(selectedPresetSettings);
    }

}