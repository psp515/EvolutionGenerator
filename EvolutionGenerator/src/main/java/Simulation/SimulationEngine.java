package Simulation;


import Elements.Animal;
import Elements.Food;
import Enums.MapType;
import Interfaces.ISimulationEngine;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapSimulations;
import Maps.MapSettings;

public class SimulationEngine implements ISimulationEngine, IMapSimulations {

    boolean isRunning;

    IMap map;
    Animal[] animals;
    Food[] food;

    public SimulationEngine(MapSettings mapSettings, MapType selectedMap){

    }

    @Override
    public void simulateDeaths() {

    }
    @Override
    public void moveAnimals() {

    }
    @Override
    public void simulateEating() {

    }
    @Override
    public void simulateBorns() {

    }
    @Override
    public void growFood() {

    }

    @Override
    public void run() {

        moveAnimals();
        simulateEating();
        simulateDeaths();
        simulateBorns();
        growFood();

        //TODO : notify view of updates

        if(!isRunning)
            return;

    }

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
