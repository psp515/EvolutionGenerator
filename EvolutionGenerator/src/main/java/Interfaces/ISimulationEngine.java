package Interfaces;

public interface ISimulationEngine {
    void removeAnimals();
    void moveAnimals();
    void eatGrass();
    void copulation();
    void generateGrass();


    void startSimulation();
    void stopSimulation();

    void run();
}
