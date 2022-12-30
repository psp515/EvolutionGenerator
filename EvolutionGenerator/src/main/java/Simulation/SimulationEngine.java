package Simulation;


import Elements.Animal;
import Elements.Food;
import Interfaces.ISimulationEngine;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapSimulations;
import Models.MapStatistics;
import Models.SimulationSettings;
import Tools.SingleFoodField;
import Tools.Vector2d;

import static Tools.Randomizer.getRandomNumber;

public class SimulationEngine implements ISimulationEngine, IMapSimulations, Runnable {

    boolean isRunning;
    int simulationDay;

    IMap map;
    MapStatistics mapStatistics;

    Animal[] animals;
    Food[] food;

    SingleFoodField[][] mapFields;

    Animal markedAnimal;
    SimulationSettings simulationSettings;

    IFoodGenerator foodGenerator;

    public SimulationEngine(SimulationSettings settings){

        this.simulationSettings = settings;
        this.mapStatistics = new MapStatistics(settings.width, settings.height);

        mapFields = new SingleFoodField[simulationSettings.width][simulationSettings.height];

        map = simulationSettings.
                mapOption.
                getClassRepresentation(simulationSettings
                        .generateMapSettings(), mapFields);

        foodGenerator = simulationSettings.growingOptions.getClassRepresentation(mapFields, mapStatistics);

        generateStartingAnimals();
        growFood();

        markedAnimal = null;
    }

    //region Initialization
    private void generateStartingAnimals() {
        for(int i = 0; i < simulationSettings.startingAnimals; i++)
        {
            Vector2d animalPosition = new Vector2d(map.getStartBound(), map.getEndBound());
            Animal newAnimal = new Animal(
                    map,
                    animalPosition,
                    simulationSettings.genesOptions
                            .getClassRepresentation(generateGenotype(simulationSettings.gensLength)),
                    simulationSettings.movementsOptions.getClassRepresentation(),
                    simulationDay,
                    simulationSettings.startingEnregy);

            map.placeElement(newAnimal);

            mapStatistics.animalsOnMap += 1;
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
                    {
                        animal.eat(field.getFood());
                        mapStatistics.foodOnMap -= 1;
                    }
                }
    }

    @Override
    public void simulateDeaths()
    {
        for(Animal animal: animals)
            if(animal.getEnergy() <= 0)
                map.removeElement(animal);
    }

    @Override
    public void simulateBorns() {
        // dla kazdego fielda jezeli sa 2 zwierzki spelnajace warunki to wywołujemy metode copulate. i efekt wstawiamy do mapy
    }
    @Override
    public void growFood() {

        for(int i = 0; i < simulationSettings.dailyFoodGrow; i++)
        {
            Food food = foodGenerator.growFood();

            if(food == null){
                // nie ma miejsca na mapie
                break;
            }
            else {
                map.placeElement(food);
                mapStatistics.foodOnMap += 1;
            }
        }
    }

    @Override
    public void run() {

        moveAnimals();

        simulateEating();

        simulateDeaths();

        simulateBorns();

        growFood();


        //TODO : notify view of updates, IPropertyChanged


        if(simulationSettings.saveToCsv)
        {

            //TODO save to csv

        }

        //UpdateStatistics

        mapStatistics.dayBorns = 0;
        mapStatistics.dayDeaths = 0;


        if(!isRunning)
            return;

        this.run();
    }

    public void MarkMostPopularGenotype(){}

    public void DeMarkMostPopularGenotype(){}

    //TO mozliwe ze tu ma nie byc
    @Override
    public void startSimulation() {
        if(isRunning)
            return;

        isRunning = true;
    }

    @Override
    public void stopSimulation() {
        isRunning = false;
    }
}
