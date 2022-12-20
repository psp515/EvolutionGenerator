package Interfaces.Map;

import Elements.Animal;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;

public interface IMap {

    void moveAnimal(Animal element);
    void mutate(IGenes element);
    void placeElement(IMapElement element);
    void removeElement(IMapElement element);
    IMapElement[] getElementsAt(Vector2d position);
    IMapElement[] getElements();
    int getEnergyFromFood();
    int getCopulationCost();
    int getMinimalCopulationPower();
}
