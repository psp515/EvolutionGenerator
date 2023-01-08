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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static Tools.Randomizer.getRandomNumber;
import static java.lang.System.out;

public class SimulationEngine implements IMapSimulations, Runnable {

    int simulationDay;

    IMap map;
    MapStatistics mapStatistics;

    ArrayList<Animal> animals;

    ArrayList<Animal> deadAnimals;

    SingleFoodField[][] mapFields;

    Animal markedAnimal;
    public final SimulationSettings _simulationSettings;

    IFoodGenerator foodGenerator;
    SimulationStatus isRunning;

    IPropertyChanged observer;


    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public Animal getMarkledAnimal(){
        return this.markedAnimal;
    }
    public int getSimulationDay(){
        return this.simulationDay;
    }

    public SingleFoodField[][] getMapFields(){return this.mapFields;}

    public MapStatistics getMapStatistics(){
        return this.mapStatistics;
    }

    public void setMarkedAnimal(Animal animal)
    {
        //TODO : check if in animals
        this.markedAnimal = animal;
    }


    public SimulationEngine(SimulationSettings settings, SimulationStatus isRunning, IPropertyChanged observer){

        this.observer = observer;
        this.isRunning = isRunning;

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

        markedAnimal = null;
    }

    //region Initialization

    private void generateStartingFood()
    {
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

        //TODO ELETE

        Animal newAnimal = new Animal(
                map,
                new Vector2d(1,0),
                _simulationSettings.genesOptions
                        .getClassRepresentation(new int[]{0, 0, 0, 0}),
                _simulationSettings.movementsOptions.getClassRepresentation(),
                simulationDay,
                _simulationSettings.startingEnregy);

        animals.add(newAnimal);
        map.placeElement(newAnimal);


        Animal newAnimal2 = new Animal(
                map,
                new Vector2d(1,0),
                _simulationSettings.genesOptions
                        .getClassRepresentation(new int[]{0, 0, 0, 0}),
                _simulationSettings.movementsOptions.getClassRepresentation(),
                simulationDay,
                _simulationSettings.startingEnregy);

        animals.add(newAnimal2);
        map.placeElement(newAnimal2);

        markedAnimal = newAnimal;
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

        for(int i = 0; i < map.getMapSettings().width; i++) {
            for(int j = 0; j < map.getMapSettings().width; j++)
            {
                var field = mapFields[i][j];
                var elements = field.getElements();

                if(elements.length < 2)
                    return;

                out.println("Simulate borns");
                out.println(elements.length);

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

                        //TODO : find popular genotype;
                    }
                }
            }

        //TODO FIX
        mapStatistics.animalsOnMap = animals.size();
    }


    @Override
    public void run() {
        /*
        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                while(isRunning.isRunning)
                {
                    out.println("startDay");
                    simulationDay += 1;
                    mapStatistics.dayBorns = 0;
                    mapStatistics.dayDeaths = 0;
                    mapStatistics.placesFreeFromAnimalCount = 0;

                    moveAnimals();
                    out.println("1");
                    simulateEating();

                    out.println("2");
                    simulateDeaths();
                    out.println("3");
                    simulateBorns();
                    out.println("4");
                    growFood();
                    out.println("5");

                    updateStatistics();

                    out.println("data");

                    observer.propertyChanged();

                    if(_simulationSettings.saveToCsv)
                    {
                        //TODO save to csv
                    }

                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {}
                    out.println("endsleep");
                }

                return null;
            }
        };

        new Thread(task).run();*/
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

        observer.propertyChanged();

        if(_simulationSettings.saveToCsv)
        {
            //TODO save to csv
        }


        out.println("endsleep");
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
