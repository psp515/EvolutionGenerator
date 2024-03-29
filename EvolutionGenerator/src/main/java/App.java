import ElementsExtensions.AnimalMovement.AnimalMovement;
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
import org.json.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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

    private void loadPresets() throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader("src/main/resources/simSet.json"));

        // typecasting obj to JSONObject
        JSONObject jo = (JSONObject) obj;
        JSONObject ss1 = (JSONObject) jo.get("ss1");
        JSONObject ss2 = (JSONObject) jo.get("ss2");

        var ss_1 = new SimulationSettings();
        var ss_2 = new SimulationSettings();



        ss_1.width = ((Number) ss1.get("width")).intValue();
        ss_1.height = ((Number) ss1.get("height")).intValue();
        ss_1.moveCost = ((Number) ss1.get("moveCost")).intValue();
        ss_1.energyFromFood = ((Number) ss1.get("energyFromFood")).intValue();
        ss_1.copulationMinimalEnergy = ((Number) ss1.get("copulationMinimalEnergy")).intValue();
        ss_1.copulationCostEnregy = ((Number) ss1.get("copulationCostEnregy")).intValue();
        ss_1.startingEnregy = ((Number) ss1.get("startingEnregy")).intValue();
        ss_1.maxEnregy = ((Number) ss1.get("maxEnregy")).intValue();
        ss_1.startingFood = ((Number) ss1.get("startingFood")).intValue();
        ss_1.dailyFoodGrow = ((Number) ss1.get("dailyFoodGrow")).intValue();
        ss_1.startingAnimals = ((Number) ss1.get("startingAnimals")).intValue();
        ss_1.movementsOptions = returnAniMovOpt((String) ss1.get("movementsOptions"));
        ss_1.genesOptions = returnGenOpt((String) ss1.get("genesOptions"));
        ss_1.mapOption = returnMapOpt((String) ss1.get("mapOption"));
        ss_1.growingOptions = returnFoodGrOpt((String) ss1.get("growingOptions"));
        ss_1.gensLength = ((Number) ss1.get("gensLength")).intValue();
        ss_1.saveToCsv = (boolean) ss1.get("saveToCsv");

        ss_2.width = ((Number) ss2.get("width")).intValue();
        ss_2.height = ((Number) ss2.get("height")).intValue();
        ss_2.moveCost = ((Number) ss2.get("moveCost")).intValue();
        ss_2.energyFromFood = ((Number) ss2.get("energyFromFood")).intValue();
        ss_2.copulationMinimalEnergy = ((Number) ss2.get("copulationMinimalEnergy")).intValue();
        ss_2.copulationCostEnregy = ((Number) ss2.get("copulationCostEnregy")).intValue();
        ss_2.startingEnregy = ((Number) ss2.get("startingEnregy")).intValue();
        ss_2.maxEnregy = ((Number) ss2.get("maxEnregy")).intValue();
        ss_2.startingFood = ((Number) ss2.get("startingFood")).intValue();
        ss_2.dailyFoodGrow = ((Number) ss2.get("dailyFoodGrow")).intValue();
        ss_2.startingAnimals = ((Number) ss2.get("startingAnimals")).intValue();
        ss_2.movementsOptions = returnAniMovOpt((String) ss2.get("movementsOptions"));
        ss_2.genesOptions = returnGenOpt((String) ss2.get("genesOptions"));
        ss_2.mapOption = returnMapOpt((String) ss2.get("mapOption"));
        ss_2.growingOptions = returnFoodGrOpt((String) ss2.get("growingOptions"));
        ss_2.gensLength = ((Number) ss2.get("gensLength")).intValue();
        ss_2.saveToCsv = (boolean) ss2.get("saveToCsv");

        var sw1 = new SettingsWrapper();
        sw1.name = "Default Combination";
        sw1.settings = ss_1;

        var sw2 = new SettingsWrapper();
        sw2.name = "Modification";
        sw2.settings = ss_2;

        optionalSettings.add(sw1);
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


        IntEntry widthEntry = new IntEntry("Map Width", 40,5,80);
        IntEntry heightEntry = new IntEntry("Map Height", 40,5,80);
        IntEntry moveCostEntry = new IntEntry("Move Cost", 1,1,10);
        IntEntry energyFromFoodEntry = new IntEntry("Energy From Food", 50,1,100);
        IntEntry copulationMinimalEnergyEntry = new IntEntry("Copulation energy requirements", 80,1,100);
        IntEntry copulationCostEnregyEntry = new IntEntry("Copulation Cost", 39,1,100);
        IntEntry startingEnregyEntry = new IntEntry("Starting Animal Energy", 50,1,100);
        IntEntry maxEnregyEntry = new IntEntry("Maximal Animal Energy", 500,100,1000);
        IntEntry startingFoodEntry = new IntEntry("Starting Food Objects on Map", 1,1,80*80);
        IntEntry dailyFoodGrowEntry = new IntEntry("Daily Food Grow", 1,1,80*80);
        IntEntry startingAnimalsEntry = new IntEntry("Starting Animals Count", 1,1,80*80);
        IntEntry genesLengthEntry = new IntEntry("Gen Length", 1,1,32);

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
        genesOptionsSelector.setPromptText("Genes Options");
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

            out.println(x.width);

            out.println(x.height);

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

    public AnimalMovementOptions returnAniMovOpt (String opt){
        return switch(opt){
            case ("SLIGHT_MADNESS") -> AnimalMovementOptions.SLIGHT_MADNESS;
            case ("FULL_FATE") -> AnimalMovementOptions.FULL_FATE;
            default -> AnimalMovementOptions.DEFAULT;
        };
    }

    public GenesOptions returnGenOpt (String opt){
        return switch(opt){
            case ("DISCOURAGED_GENOTYPE") -> GenesOptions.DISCOURAGED_GENOTYPE;
            case ("NORMAL_GENOTYPE") -> GenesOptions.NORMAL_GENOTYPE;
            default -> GenesOptions.DEFAULT;
        };
    }

    public MapOptions returnMapOpt (String opt){
        return switch(opt){
            case ("HELL") -> MapOptions.HELL;
            case ("EARTH") -> MapOptions.EARTH;
            default -> MapOptions.DEFAULT;
        };
    }

    public FoodGrowOptions returnFoodGrOpt (String opt){
        return switch(opt){
            case ("DEATH_BODIES") -> FoodGrowOptions.DEATH_BODIES;
            case ("LINEAR_GROW") -> FoodGrowOptions.LINEAR_GROW;
            case ("EQUATORS") -> FoodGrowOptions.EQUATORS;
            default -> FoodGrowOptions.DEFAULT;
        };
    }

}

// jakieś wcześniejsze wartości zanim wklepałem to do JSONa

//        ss.width = 30;
//        ss.height = 30;
//        ss.moveCost = 2;
//        ss.energyFromFood = 50;
//        ss.copulationMinimalEnergy = 10;
//        ss.copulationCostEnregy = 10;
//        ss.startingEnregy = 67;
//        ss.maxEnregy = 300;
//        ss.startingFood = 100;
//        ss.dailyFoodGrow = 15;
//        ss.startingAnimals = 100;
//        ss.movementsOptions = AnimalMovementOptions.DEFAULT;
//        ss.genesOptions = GenesOptions.DEFAULT;
//        ss.mapOption = MapOptions.DEFAULT;
//        ss.growingOptions = FoodGrowOptions.DEFAULT;
//        ss.gensLength = 8;
//        ss.saveToCsv = false;
