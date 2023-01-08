import Enums.AnimalMovementOptions;
import Enums.FoodGrowOptions;
import Enums.GenesOptions;
import Enums.MapOptions;
import Gui.Others.IntEntry;
import Gui.Simulation.SimulationView;
import Models.SettingsWrapper;
import Models.SimulationSettings;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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

    ComboBox genesOptionsSelector;
    ObservableList<String> genesOptions = FXCollections.observableArrayList();

    ComboBox savingOptionsSelector;
    ObservableList<String> savingOptions = FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception {

        loadPresets();
        loadOptions();

        HBox horizontal = new HBox();
        horizontal.setPadding(new Insets(20,20,20,20));
        horizontal.setSpacing(20);
        horizontal.getChildren().add(PresetForm());
        horizontal.getChildren().add(CustomForm());

        ScrollPane scrollPane = new ScrollPane(horizontal);

        primaryStage.setScene(new Scene(scrollPane));
        primaryStage.show();

    }

    private void loadOptions() {

        for(var value : FoodGrowOptions.values())
            foodGenerationOptions.add(value.toString());


        for(var value : AnimalMovementOptions.values())
            movementOptions.add(value.toString());

        for(var value : MapOptions.values())
            mapOptions.add(value.toString());

        for(var value : GenesOptions.values())
            genesOptions.add(value.toString());

        savingOptions.add("Save!");
        savingOptions.add("Don't Save.");

    }

    private void loadPresets() {
        //TODO : json
        var ss = new SimulationSettings();
        ss.width = 50;
        ss.height = 50;
        ss.moveCost = 1;
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
        ss2.moveCost = 1;
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
    public VBox CustomForm() {
        VBox form = new VBox();
        form.setAlignment(Pos.CENTER);

        var title = new Label("Load settings from preset");
        title.setStyle("-fx-font-weight: bold");

        Button startButton = new Button("Start Custom Simulation");

        IntEntry widthEntry = new IntEntry("Map Width", 40,20,200);
        IntEntry heightEntry = new IntEntry("Map Height", 40,20,200);

        IntEntry moveCostEntry = new IntEntry("Move Cost", 10,1,10);
        IntEntry energyFromFoodEntry = new IntEntry("Energy From Food", 50,1,100);
        IntEntry copulationMinimalEnergyEntry = new IntEntry("Copulation energy requirements", 80,1,100);
        IntEntry copulationCostEnregyEntry = new IntEntry("Copulation Cost", 39,1,100);
        IntEntry startingEnregyEntry = new IntEntry("Starting Animals Energy", 100,1,100);
        IntEntry maxEnregyEntry = new IntEntry("Maximal Animal Energy", 500,1,1000);

        IntEntry startingFoodEntry = new IntEntry("Starting Food Objects on Map", 1,1,200*200);
        IntEntry dailyFoodGrowEntry = new IntEntry("Daily Food Grow", 1,1,200*200);
        IntEntry startingAnimalsEntry = new IntEntry("Starting Animal Energy", 1,1,200*200);

        IntEntry genesLengthEntry = new IntEntry("Map Width", 1,1,32);

        VBox mo = new VBox();
        mo.setPadding(new Insets(4,10,4,10));
        Label mosDescription = new Label("Map Type");
        mapOptionsSelector = new ComboBox();
        mapOptionsSelector.setPromptText("Select Map Type");
        mapOptionsSelector.getItems().addAll(mapOptions);

        VBox f = new VBox();
        f.setPadding(new Insets(4,10,4,10));
        Label fgosDescription = new Label("Food Growing  Type");
        foodGenerationOptionsSelector = new ComboBox();
        foodGenerationOptionsSelector.setPromptText("Select Food Growing Options");
        foodGenerationOptionsSelector.getItems().addAll(foodGenerationOptions);


        VBox m = new VBox();
        m.setPadding(new Insets(4,10,4,10));
        Label moDescription = new Label("Animal Movement Type");
        movementOptionsSelector = new ComboBox();
        movementOptionsSelector.setPromptText("Select Movement Type");
        movementOptionsSelector.getItems().addAll(movementOptions);

        VBox g = new VBox();
        g.setPadding(new Insets(4,10,4,10));
        Label gDescription = new Label("Genes Options");
        genesOptionsSelector = new ComboBox();
        genesOptionsSelector.setPromptText("Saving Options");
        genesOptionsSelector.getItems().addAll(genesOptions);

        VBox sd = new VBox();
        sd.setPadding(new Insets(4,10,4,10));
        Label sdDescription = new Label("Save Data To CSV");
        savingOptionsSelector = new ComboBox();
        savingOptionsSelector.setPromptText("Saving Options");
        savingOptionsSelector.getItems().addAll(savingOptions);



        //Adding To View

        form.getChildren().add(title);

        form.getChildren().add(widthEntry);
        form.getChildren().add(heightEntry);

        form.getChildren().add(moveCostEntry);
        form.getChildren().add(energyFromFoodEntry);
        form.getChildren().add(copulationMinimalEnergyEntry);
        form.getChildren().add(copulationCostEnregyEntry);
        form.getChildren().add(startingEnregyEntry);
        form.getChildren().add(maxEnregyEntry);
        form.getChildren().add(startingFoodEntry);
        form.getChildren().add(dailyFoodGrowEntry);
        form.getChildren().add(startingAnimalsEntry);

        mo.getChildren().add(mosDescription);
        mo.getChildren().add(mapOptionsSelector);
        form.getChildren().add(mo);
        f.getChildren().add(fgosDescription);
        f.getChildren().add(foodGenerationOptionsSelector);
        form.getChildren().add(f);
        m.getChildren().add(moDescription);
        m.getChildren().add(movementOptionsSelector);
        form.getChildren().add(m);

        g.getChildren().add(gDescription);
        g.getChildren().add(genesOptionsSelector);
        form.getChildren().add(g);

        sd.getChildren().add(sdDescription);
        sd.getChildren().add(savingOptionsSelector);
        form.getChildren().add(sd);

        form.getChildren().add(genesLengthEntry);
        form.getChildren().add(startButton);

        // Actions
        startButton.setOnAction( (e) ->
        {
            var x = new SimulationSettings();

            if(widthEntry.isValid())
                x.width = widthEntry.getValue();
            else
            {
                showError("Width should be between " + widthEntry.min +" and " + widthEntry.max);
                return;
            }

            if(heightEntry.isValid())
                x.height = heightEntry.getValue();
            else
            {
                showError("Height should be between " + heightEntry.min +" and " + heightEntry.max);
                return;
            }

            if(moveCostEntry.isValid())
                x.moveCost = moveCostEntry.getValue();
            else
            {
                showError("Move Cost should be between " + moveCostEntry.min +" and " + moveCostEntry.max);
                return;
            }

            if(energyFromFoodEntry.isValid())
                x.energyFromFood = energyFromFoodEntry.getValue();
            else
            {
                showError("Enregy from food should be between " + energyFromFoodEntry.min +" and " + energyFromFoodEntry.max);
                return;
            }

            if(copulationMinimalEnergyEntry.isValid())
                x.copulationMinimalEnergy = copulationMinimalEnergyEntry.getValue();
            else
            {
                showError("Copulation minimal energy should be between " + copulationMinimalEnergyEntry.min +" and " + copulationMinimalEnergyEntry.max);
                return;
            }

            if(copulationCostEnregyEntry.isValid())
                x.copulationCostEnregy = copulationCostEnregyEntry.getValue();
            else
            {
                showError("Copulation cost should be between " + copulationCostEnregyEntry.min +" and " + copulationCostEnregyEntry.max);
                return;
            }

            if(copulationCostEnregyEntry.getValue() > copulationMinimalEnergyEntry.getValue())
            {
                showError("Copulation cost energy must be lower or requal copulation minimal energy.");
                return;
            }

            if(startingEnregyEntry.isValid())
                x.startingEnregy = startingEnregyEntry.getValue();
            else
            {
                showError("Starting Energy should be between " + startingEnregyEntry.min +" and " + startingEnregyEntry.max);
                return;
            }

            if(maxEnregyEntry.isValid())
                x.maxEnregy = maxEnregyEntry.getValue();
            else
            {
                showError("Max Energy should be between " + maxEnregyEntry.min +" and " + maxEnregyEntry.max);
                return;
            }

            if(startingFoodEntry.isValid()) {
                x.startingFood = startingFoodEntry.getValue();
                if(x.startingFood > x.width * x.height)
                {
                    showError("Starting food must be lower than number of fields");
                    return;
                }
            }
            else
            {
                showError("Starting food should be between " + startingFoodEntry.min +" and " + startingFoodEntry.max);
                return;
            }

            if(dailyFoodGrowEntry.isValid()) {
                x.dailyFoodGrow = dailyFoodGrowEntry.getValue();
                if(x.dailyFoodGrow > x.width * x.height)
                {
                    showError("Food grow must be lower than number of fields");
                    return;
                }
            }
            else
            {
                showError("Daily food grow should be between " + dailyFoodGrowEntry.min +" and " + dailyFoodGrowEntry.max);
                return;
            }

            if(startingAnimalsEntry.isValid()){
                x.startingAnimals = startingAnimalsEntry.getValue();
                if(x.startingAnimals > x.width * x.height)
                {
                    showError("Map must start with less animals than number of fields");
                    return;
                }
            }
            else
            {
                showError("Starting animals should be between " + startingAnimalsEntry.min +" and " + startingAnimalsEntry.max);
                return;
            }

            if(genesLengthEntry.isValid())
                x.gensLength = genesLengthEntry.getValue();
            else
            {
                showError("Genes length should be between " + genesLengthEntry.min +" and " + genesLengthEntry.max);
                return;
            }

            if(mapOptionsSelector.getValue() != null){
                x.mapOption = Arrays.stream(MapOptions.values())
                        .filter(p-> p.toString().equals(mapOptionsSelector.getValue()))
                        .findFirst()
                        .orElse(MapOptions.DEFAULT);
            }
            else
            {
                showError("Please select map type.");
                return;
            }

            if(foodGenerationOptionsSelector.getValue() != null){
                x.growingOptions = Arrays.stream(FoodGrowOptions.values())
                        .filter(p-> p.toString().equals(mapOptionsSelector.getValue()))
                        .findFirst()
                        .orElse(FoodGrowOptions.DEFAULT);
            }
            else
            {
                showError("Please select food generation type.");
                return;
            }

            if(movementOptionsSelector.getValue() != null) {
                x.movementsOptions = Arrays.stream(AnimalMovementOptions.values())
                        .filter(p-> p.toString().equals(mapOptionsSelector.getValue()))
                        .findFirst()
                        .orElse(AnimalMovementOptions.DEFAULT);
            }
            else
            {
                showError("Please select movement type.");
                return;
            }

            if(genesOptionsSelector.getValue() != null) {
                x.genesOptions = Arrays.stream(GenesOptions.values())
                        .filter(p-> p.toString().equals(mapOptionsSelector.getValue()))
                        .findFirst()
                        .orElse(GenesOptions.DEFAULT);
            }
            else
            {
                showError("Please select genes type.");
                return;
            }

            if(savingOptionsSelector.getValue() != null){
                if(savingOptionsSelector.getValue().equals("Save!"))
                    x.saveToCsv = true;
                else
                    x.saveToCsv = false;
            }
            else
            {
                showError("Please select saving.");
                return;
            }

            StartCustomSimulation(x);
        });


        return form;
    }

    public void showError(String errorMessage) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(errorMessage);
        a.show();
    }


    public void StartCustomSimulation(SimulationSettings settings) {

        if(!isSettingsValid())
            return;

        new SimulationView(settings);
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

        if(selectedPresetSettings == null) {
            showError("Please select preset.");
            return;
        }

        new SimulationView(selectedPresetSettings.settings);
    }

}