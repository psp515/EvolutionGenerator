package Interfaces.Map;

import Interfaces.Map.IMapElement;

public interface IMapMoveElement extends IMapElement {
    void move();
    void useEnergy(int energy);
    int getEnergy();
}
