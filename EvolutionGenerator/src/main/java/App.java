import Enums.AnimalMovementOptions;
import Enums.GenesOptions;
import Enums.MapOptions;
import Gui.Others.IntEntry;
import Gui.Others.NumberTextField;
import Gui.Simulation.SimulationView;
import Models.SettingsWrapper;
import Models.SimulationSettings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;

import static java.lang.System.out;

public class App extends Application
{

    ArrayList<SettingsWrapper> optionalSettings = new ArrayList<>();
    ComboBox presetSelector;
    ObservableList<String> optionalSettingsNames = FXCollections.observableArrayList();

    ComboBox mapOptionsSelector;
    ObservableList<String> mapOptions = FXCollections.observableArrayList();

    ComboBox movementOptionsSelector;
    ObservableList<String> movementOptions = FXCollections.observableArrayList();

    ComboBox foodGenerationOptionsSelector;
    ObservableList<String> foodGenerationOptions = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {

        loadPresets();

        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(20,20,20,20));
        horizontal.setSpacing(20);
        horizontal.getChildren().add(PresetForm());
        horizontal.getChildren().add(CustomForm());

        primaryStage.setScene(new Scene(horizontal));
        primaryStage.show();

    }

    private void loadPresets() {
        //TODO : json
        var ss = new SimulationSettings();
        ss.width = 50;
        ss.height = 50;
        ss.moveEnergy = 1;
        ss.energyFromFood = 50;
        ss.copulationMinimalEnergy = 80;
        ss.copulationCostEnregy = 50;
        ss.startingEnregy = 100;
        ss.maxEnregy = 300;
        ss.startingFood = 30;
        ss.dailyFoodGrow = 30;
        ss.startingAnimals = 30;
        ss.movementsOptions = AnimalMovementOptions.DEFAULT;
        ss.genesOptions = GenesOptions.DEFAULT;
        ss.mapOption = MapOptions.DEFAULT;
        ss.gensLength = 16;
        ss.saveToCsv = false;

        var sw = new SettingsWrapper();
        sw.name = "Default";
        sw.settings = ss;

        var ss2 = new SimulationSettings();
        ss2.width = 50;
        ss2.height = 50;
        ss2.moveEnergy = 1;
        ss2.energyFromFood = 50;
        ss2.copulationMinimalEnergy = 80;
        ss2.copulationCostEnregy = 50;
        ss2.startingEnregy = 100;
        ss2.maxEnregy = 300;
        ss2.startingFood = 30;
        ss2.dailyFoodGrow = 30;
        ss2.startingAnimals = 30;
        ss2.movementsOptions = AnimalMovementOptions.DEFAULT;
        ss2.genesOptions = GenesOptions.DEFAULT;
        ss2.mapOption = MapOptions.DEFAULT;
        ss2.gensLength = 16;
        ss2.saveToCsv = false;

        var sw2 = new SettingsWrapper();
        sw2.name = "Default2";
        sw2.settings = ss2;

        optionalSettings.add(sw);
        optionalSettings.add(sw2);

        for(var item : optionalSettings)
            optionalSettingsNames.add(item.name);

    }

    //Custom
    public VBox CustomForm()
    {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);

        var title = new Label("Load settings from preset");
        title.setStyle("-fx-font-weight: bold");

        Button startButton = new Button("Start Custom Simulation");

        IntEntry widthEntry = new IntEntry("Map Width", 20,20,200);
        IntEntry heightEntry = new IntEntry("Map Height", 20,20,200);

        IntEntry moveEnergyEntry = new IntEntry("Move Cost", 1,1,10);
        IntEntry energyFromFoodEntry = new IntEntry("Energy From Food", 50,1,100);
        IntEntry copulationMinimalEnergyEntry = new IntEntry("Copulation energy requirements", 80,1,100);
        IntEntry copulationCostEnregyEntry = new IntEntry("Copulation Cost", 50,1,100);
        IntEntry startingEnregyEntry = new IntEntry("Starting Animals Energy", 100,1,100);
        IntEntry maxEnregyEntry = new IntEntry("Maximal Animal Energy", 500,1,1000);

        IntEntry startingFoodEntry = new IntEntry("Starting Food Objects on Map", 20,20,200);
        IntEntry dailyFoodGrowEntry = new IntEntry("Daily Food Grow", 20,20,200);
        IntEntry startingAnimalsEntry = new IntEntry("Starting Animal Energy", 20,20,200);

        IntEntry genesLengthEntry = new IntEntry("Map Width", 1,1,32);

        foodGenerationOptionsSelector = new ComboBox();
        foodGenerationOptionsSelector.setPromptText("Select Preset");
        foodGenerationOptionsSelector.getItems().addAll(mapOptions);

        foodGenerationOptionsSelector = new ComboBox();
        foodGenerationOptionsSelector.setPromptText("Select Preset");
        foodGenerationOptionsSelector.getItems().addAll(optionalSettingsNames);

        foodGenerationOptionsSelector = new ComboBox();
        foodGenerationOptionsSelector.setPromptText("Select Preset");
        foodGenerationOptionsSelector.getItems().addAll(optionalSettingsNames);

        //Adding To View

        form.getChildren().add(title);

        form.getChildren().add(widthEntry);
        form.getChildren().add(heightEntry);

        form.getChildren().add(moveEnergyEntry);
        form.getChildren().add(energyFromFoodEntry);
        form.getChildren().add(copulationMinimalEnergyEntry);
        form.getChildren().add(copulationCostEnregyEntry);
        form.getChildren().add(startingEnregyEntry);
        form.getChildren().add(maxEnregyEntry);
        form.getChildren().add(startingFoodEntry);
        form.getChildren().add(dailyFoodGrowEntry);
        form.getChildren().add(startingAnimalsEntry);

        form.getChildren().add(genesLengthEntry);
        form.getChildren().add(startButton);

        // Actions
        startButton.setOnAction( (e) ->
        {
            var x = new SimulationSettings();

            StartCustomSimulation(x);
        });


        return form;
    }
    public void StartCustomSimulation(SimulationSettings settings) {

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
    public VBox PresetForm() {
        VBox form = new VBox();
        form.setPadding(new Insets(20,20,20,20));
        form.setAlignment(Pos.CENTER);
        form.setSpacing(15);

        var firstTitle = new Label("Load settings from preset");
        firstTitle.setStyle("-fx-font-weight: bold");

        presetSelector = new ComboBox();
        presetSelector.setPromptText("Select Preset");
        presetSelector.getItems().addAll(optionalSettingsNames);


        Button startButton = new Button("Start Preset Simulation");

        //Adding To View
        form.getChildren().add(firstTitle);
        form.getChildren().add(presetSelector);
        form.getChildren().add(startButton);

        // Actions
        startButton.setOnAction( (e) ->
        {
            StartPresetSimulation();
        });

        return form;
    }
    public void StartPresetSimulation() {
        var selectedPresetSettings = (SettingsWrapper) optionalSettings
                .stream()
                .filter(sw-> sw.name.equals(presetSelector.getValue()))
                .findFirst()
                .orElse(null);

        if(selectedPresetSettings == null)
            return;

        new SimulationView(selectedPresetSettings.settings);
    }

}