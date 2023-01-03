package Simulation;


import Elements.Animal;
import Elements.Food;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapSimulations;
import Models.MapStatistics;
import Models.SimulationSettings;
import Tools.SingleFoodField;
import Tools.Vector2d;

import java.util.ArrayList;

import static Tools.Randomizer.getRandomNumber;

public class SimulationEngine implements IMapSimulations, Runnable {

    int simulationDay;

    IMap map;
    MapStatistics mapStatistics;

    ArrayList<Animal> animals;

    ArrayList<Animal> deadAnimals;

    SingleFoodField[][] mapFields;

    Animal markedAnimal;
    SimulationSettings simulationSettings;

    IFoodGenerator foodGenerator;

    public SimulationEngine(SimulationSettings settings){

        animals = new ArrayList<>();
        deadAnimals = new ArrayList<>();

        this.simulationSettings = settings;
        this.mapStatistics = new MapStatistics(settings.width, settings.height);

        mapFields = new SingleFoodField[simulationSettings.width][simulationSettings.height];

        map = simulationSettings.
                mapOption.
                getClassRepresentation(simulationSettings
                        .generateMapSettings(), mapFields);

        foodGenerator = simulationSettings.growingOptions.getClassRepresentation(mapFields, mapStatistics);

        generateStartingAnimals();
        growFood(map.getStartBound(),map.getEndBound());

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
                    }
                }
    }


    @Override
    public void simulateDeaths()
    {
        //Nowa lista bo nie da sie iterowac po zmieniajacej kolekcji
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

        for(SingleFoodField[] row : mapFields)
            for(SingleFoodField field : row)
            {
                var fieldAniamls = field.getAnimals();

                if(fieldAniamls.size() < 2)
                    return;

                fieldAniamls.sort((Animal a1, Animal a2) -> a1.getEnergy() < a2.getEnergy() ? -1 : 1);

                var p1 = fieldAniamls.get(0);
                var p2 = fieldAniamls.get(1);

                var ch = p1.copulate(p2, simulationDay);

                if(ch != null){
                    animals.add(ch);
                    mapStatistics.dayBorns += 1;
                }
            }
    }
    @Override
    public void growFood(Vector2d startpoint, Vector2d endpoint) {

        for(int i = 0; i < simulationSettings.dailyFoodGrow; i++)
        {
            Food food = foodGenerator.growFood(startpoint, endpoint, map, this.simulationDay);

            if(food == null){
                // nie ma miejsca na mapie na jedzenie
                break;
            }
            else {
                map.placeElement(food);
                mapStatistics.safeFoodGrow(food);
            }
        }
    }

    void UpdateStatistics()
    {
        mapStatistics.animalsOnMap = animals.size();
        int totalEnergy = 0;
        int totalDaysLived = 0;

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

                        //TODO : find popular genotype;
                    }
                }
            }
    }

    @Override
    public void run() {
        if(simulationDay == Integer.MAX_VALUE)
            return;

        simulationDay += 1;
        mapStatistics.dayBorns = 0;
        mapStatistics.dayDeaths = 0;
        mapStatistics.placesFreeFromAnimalCount = 0;

        moveAnimals();
        simulateEating();
        simulateDeaths();
        simulateBorns();
        growFood(map.getStartBound(), map.getEndBound());

        UpdateStatistics();

        //TODO : notify view of updates, IPropertyChanged

        if(simulationSettings.saveToCsv)
        {
            //TODO save to csv
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
        //TODO
        return false;
    }

    public void DeMarkMostPopularGenotype()
    {
        for(var animal : animals)
            animal.isHighlighted = false;
    }

}
