package Simulation;


import Elements.Animal;
import Elements.Food;
import Interfaces.IPropertyChanged;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapSimulations;
import Models.MapStatistics;
import Models.SimulationSettings;
import Models.SimulationStatus;
import Tools.SingleFoodField;
import Tools.Vector2d;
import javafx.application.Platform;

import java.util.*;

import static Tools.Randomizer.getRandomNumber;

public class SimulationEngine implements IMapSimulations, Runnable {

    int simulationDay;

    IMap map;
    MapStatistics mapStatistics;

    ArrayList<Animal> animals;

    ArrayList<Animal> deadAnimals;

    SingleFoodField[][] mapFields;

    Animal watchedAnimal;
    public final SimulationSettings _simulationSettings;

    IFoodGenerator foodGenerator;
    SimulationStatus status;

    IPropertyChanged observer;

    HashMap<String, Integer> genesCollection = new HashMap<String, Integer>();


    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public Animal getMarkledAnimal(){
        return this.watchedAnimal;
    }
    public int getSimulationDay(){
        return this.simulationDay;
    }

    public SingleFoodField[][] getMapFields(){return this.mapFields;}

    public MapStatistics getMapStatistics(){
        return this.mapStatistics;
    }

    public void setWatchedAnimal(Animal animal)
    {
        //TODO : check if in animals
        this.watchedAnimal = animal;
    }


    public SimulationEngine(SimulationSettings settings, SimulationStatus isRunning, IPropertyChanged observer){

        watchedAnimal = null;

        this.observer = observer;
        this.status = isRunning;

        animals = new ArrayList<>();
        deadAnimals = new ArrayList<>();

        this._simulationSettings = settings;
        this.mapStatistics = new MapStatistics(settings.width, settings.height);

        mapFields = new SingleFoodField[_simulationSettings.width][_simulationSettings.height];

        for(int i = 0; i < _simulationSettings.width;i++)
            for(int j = 0; j < _simulationSettings.height;j++)
                mapFields[i][j] = new SingleFoodField();


        map = _simulationSettings
                .mapOption
                .getClassRepresentation(_simulationSettings.generateMapSettings(), mapFields);

        foodGenerator = _simulationSettings.growingOptions.getClassRepresentation(mapFields, mapStatistics);

        generateStartingAnimals();
        generateStartingFood();
    }

    //region Initialization

    private void generateStartingFood() {
        for(int i = 0; i < _simulationSettings.startingFood; i++)
        {
            Food food = foodGenerator.growFood(map, this.simulationDay);
            if(food == null){
                break;
            }
            else {
                map.placeElement(food);
                mapStatistics.safeFoodGrow(food);
            }
        }
    }
    private void generateStartingAnimals() {
        for(int i = 0; i < _simulationSettings.startingAnimals; i++)
        {
            Vector2d animalPosition = new Vector2d(map.getStartBound(), map.getEndBound());
            Animal newAnimal = new Animal(
                    map,
                    animalPosition,
                    _simulationSettings.genesOptions
                            .getClassRepresentation(generateGenotype(_simulationSettings.gensLength)),
                    _simulationSettings.movementsOptions.getClassRepresentation(),
                    simulationDay,
                    _simulationSettings.startingEnregy);

            map.placeElement(newAnimal);
            animals.add(newAnimal);
        }
    }
    private int[] generateGenotype(int len){
        int[] genotype = new int[len];

        for(int i =0;i < len;i++)
            genotype[i] = getRandomNumber(0, 7);

        return genotype;
    }
    //endregion

    @Override
    public void moveAnimals() {
        for (Animal animal : animals)
            animal.move();
    }
    @Override
    public void simulateEating() {

        for(SingleFoodField[] row : mapFields)
            for(SingleFoodField field : row)
                if(field.containsFood()){

                    Animal animal = field.getStrongestAnimal();

                    if(animal != null)
                        animal.eat(field.getFood());

                }
    }
    @Override
    public void simulateDeaths(){
        for(Animal animal : new ArrayList<>(animals))
            if(animal.getEnergy() <= 0)
            {
                animal.setDeathDay(simulationDay);

                map.removeElement(animal);
                animals.remove(animal);
                deadAnimals.add(animal);
                mapStatistics.dayDeaths += 1;
                mapStatistics.safeDeadAnimal(animal);
            }
    }
    @Override
    public void simulateBorns() {

        for(int i = 0; i < map.getMapSettings().width; i++) {
            for(int j = 0; j < map.getMapSettings().width; j++)
            {
                var field = mapFields[i][j];
                var elements = field.getElements();

                if(elements.length < 2)
                    return;

                Animal[] fieldAniamls = new Animal[elements.length];

                for(int k=0;k < elements.length;k++)
                    fieldAniamls[k] = (Animal) elements[k];

                Arrays.sort(fieldAniamls, Comparator.comparing(Animal::getEnergy));

                var p1 = (Animal) fieldAniamls[0];
                var p2 = (Animal) fieldAniamls[1];

                var ch = p1.copulate(p2, simulationDay);

                if(ch != null){
                    animals.add(ch);
                    mapStatistics.dayBorns += 1;
                }
            }
        }
    }
    @Override
    public void growFood() {

        for(int i = 0; i < _simulationSettings.dailyFoodGrow; i++)
        {
            Food food = foodGenerator.growFood(map, this.simulationDay);

            if(food == null){
                break;
            }
            else {
                map.placeElement(food);
                mapStatistics.safeFoodGrow(food);
            }
        }
    }
    void updateStatistics() {
        genesCollection = new HashMap<>();

        mapStatistics.animalsOnMap = animals.size();
        int totalEnergy = 0;
        int totalDaysLived = 0;
        mapStatistics.foodOnMap = 0;
        mapStatistics.placesFreeFromAnimalCount = 0;

        for(Animal deadAnimal : deadAnimals)
            totalDaysLived += deadAnimal.getDeathDay() - deadAnimal.getCreationDay();

        for(SingleFoodField[] row : mapFields)
            for(SingleFoodField field : row)
            {
                if(field.containsFood())
                    mapStatistics.foodOnMap += 1;

                var fieldAnimals = field.getAnimals();

                if(field.getAnimals().size() == 0)
                    mapStatistics.placesFreeFromAnimalCount += 1;
                else
                {
                    for(Animal animal : fieldAnimals)
                    {
                        totalEnergy += animal.getEnergy();
                        totalDaysLived += simulationDay - animal.getCreationDay();

                        int[] genes = animal._genotype.getGenes();

                        String key = Arrays.toString(genes);

                        if(genesCollection.containsKey(key))
                        {
                            var val = genesCollection.get(key);
                            genesCollection.put(key, val+1);
                        }
                        else
                        {
                            genesCollection.put(key, 1);
                        }
                    }
                }
            }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(genesCollection.entrySet());
        list.sort(Map.Entry.comparingByValue());

        mapStatistics.mostPoupularGenes = list.get(list.size()-1).getKey();

        mapStatistics.animalsOnMap = animals.size();
        mapStatistics.averageEnergy = totalEnergy / animals.size();
        mapStatistics.averageLiveLength = totalDaysLived / (animals.size() + deadAnimals.size());
    }

    @Override
    public void run() {

        simulationDay += 1;
        mapStatistics.dayBorns = 0;
        mapStatistics.dayDeaths = 0;
        mapStatistics.placesFreeFromAnimalCount = 0;

        moveAnimals();
        simulateEating();
        simulateDeaths();
        simulateBorns();
        growFood();

        updateStatistics();

        Platform.runLater(() -> observer.propertyChanged());

        if(_simulationSettings.saveToCsv)
        {
            //TODO: osobny wątek
            //TODO: save to csv
        }
    }

    public void MarkMostPopularGenotype()
    {
        for(var animal : animals)
            if(isMostPopular(animal._genotype.getGenes()))
                animal.isHighlighted = true;
    }

    private boolean isMostPopular(int[] genes)
    {
        return Arrays.toString(genes).equals(mapStatistics.mostPoupularGenes);
    }

    public void DeMarkMostPopularGenotype()
    {
        for(var animal : animals)
            animal.isHighlighted = false;
    }

}
