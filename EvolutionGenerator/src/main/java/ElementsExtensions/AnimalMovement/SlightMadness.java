package ElementsExtensions.AnimalMovement;

import Elements.Animal;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;

public class SlightMadness extends AnimalMovement {
    public SlightMadness(Animal animal) {
        super(animal);
    }

    @Override
    public Vector2d nextPosition() {
        return null;
    }
}
