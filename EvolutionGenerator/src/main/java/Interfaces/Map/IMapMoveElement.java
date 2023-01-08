package Interfaces.Map;

public interface IMapMoveElement extends IMapElement {
    void move();
    void useEnergy(int energy);
    int getEnergy();
}
