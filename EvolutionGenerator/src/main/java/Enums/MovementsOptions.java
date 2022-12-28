package Enums;

import Elements.Animal;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Others.GetClassRepresentation;

public enum MovementsOptions implements GetClassRepresentation<IAnimalMovement, Animal> {
    FullFate,
    SlightMadness;

    @Override
    public IAnimalMovement getClassRepresentation(Animal animal) {
        return switch (this) {
            case FullFate -> new ElementsExtensions.AnimalMovement.FullFate(animal);
            case SlightMadness -> new ElementsExtensions.AnimalMovement.SlightMadness(animal);
        };
    }

    //TODO : to string -> nazwy dla view
}
