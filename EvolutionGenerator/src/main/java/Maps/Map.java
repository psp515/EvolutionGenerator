package Maps;

import Elements.Animal;
import Elements.MapElement;
import Enums.FoodGrowingOptions;
import Enums.MovementsOptions;
import Enums.MutationsOptions;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IAnimalMutation;
import Interfaces.Animals.IGenes;
import Interfaces.Map.IFoodGrowing;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Tools.Vector2d;

public class Map implements IMap {

    public final IAnimalMutation animalMutation;
    public final IFoodGrowing foodGrowing;
    protected IAnimalMovement animalMovement;

    protected final int width;
    protected final int height;
    protected Map(MapSettings settings)
    {
        this.width = width;
        this.height = height;

        this.animalMovement = settings.movementsOptions.getClassRepresentation();
        this.animalMutation = settings.mutationsOptions.getClassRepresentation();
        this.foodGrowing = settings.growingOptions.getClassRepresentation(this);
    }

    @Override
    public void moveAnimal(Animal element) {
        // uzywamy tu animalMovement które
        // osobna implementacja ziemia / piekło gdy wyjdzie za mape
    }

    @Override
    public void mutate(IGenes element) {
        
    }

    @Override
    public void placeElement(IMapElement element) {

    }

    @Override
    public void removeElement(IMapElement element) {

    }

    @Override
    public IMapElement[] getElementsAt(Vector2d position) {
        return new MapElement[0];
    }

    @Override
    public IMapElement[] getElements() {
        return new MapElement[0];
    }

    @Override
    public int getEnergyFromFood() {
        return 0;
    }

    @Override
    public int getCopulationCost() {
        return 0;
    }

    @Override
    public int getMinimalCopulationPower() {
        return 0;
    }
}
