package Simulation;


import Elements.Animal;
import Elements.Food;
import Enums.MapOptions;
import Interfaces.ISimulationEngine;
import Interfaces.Map.IFoodGenerator;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapSimulations;
import Models.MapStatistics;
import Models.SimulationSettings;

public class SimulationEngine implements ISimulationEngine, IMapSimulations {

    boolean isRunning;
    int simulationDay;

    IMap map;
    MapStatistics mapStatistics;

    Animal[] animals;
    Food[] food;

    MapStatistics stats;
    Animal markedAnimal;
    SimulationSettings simulationSettings;

    IFoodGenerator foodGenerator;

    public SimulationEngine(SimulationSettings settings, MapOptions selectedMap){

        this.simulationSettings = settings;
        this.mapStatistics = new MapStatistics(settings.width, settings.height);
        //Simulation settings to map settings

        GenerateAnimals();
        growFood();
    }

    private void GenerateAnimals(){}


    @Override
    public void moveAnimals() {
        //dla kazdego elementu z animals wykonujemy move
    }
    @Override
    public void simulateEating() {
        // uzywamy mapfields / dla kazdego pola mapy
        // jezeli ma animale i jedzenie to wybieramy najsilniejszego i zjadmy
        // jezeli nie to skip
    }

    @Override
    public void simulateDeaths() {
        // dla kazdego animala w animals jezeli ma energie < od 0 to usuwamy z mapy
        // zapisujemy statystyki mapy
    }

    @Override
    public void simulateBorns() {
        // dla kazdego fielda jezeli sa 2 zwierzki spelnajace warunki to wywołujemy metode copulate. i efekt wstawiamy do mapy
    }
    @Override
    public void growFood() {

        // wykorzystujemy Ifoodgrowing do wygenerowania miejsca na jedzonko i wstawiamy
    }

    @Override
    public void run() {

        moveAnimals();
        simulateEating();
        simulateDeaths();
        simulateBorns();
        growFood();

        //TODO : notify view of updates, IPropertyChanged
        //TODO : save day stats to csv file

        if(!isRunning)
            return;

    }

    public void MarkMostPopularGenotype(){}

    public void DeMarkMostPopularGenotype(){}

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
