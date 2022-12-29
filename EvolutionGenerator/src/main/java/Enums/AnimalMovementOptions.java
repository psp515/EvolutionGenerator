package Enums;

import Elements.Animal;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Others.GetClassRepresentation;
import ElementsExtensions.AnimalMovement.FullFate;
import ElementsExtensions.AnimalMovement.SlightMadness;

public enum AnimalMovementOptions implements GetClassRepresentation<IAnimalMovement, Animal> {
    DEFAULT,
    FULL_FATE,
    SLIGHT_MADNESS;

    @Override
    public IAnimalMovement getClassRepresentation(Animal animal) {
        return switch (this) {
            case FULL_FATE, DEFAULT -> new FullFate(animal);
            case SLIGHT_MADNESS -> new SlightMadness(animal);
        };
    }

    public String toString()
    {
        return switch (this) {
            case DEFAULT -> "Default";
            case FULL_FATE -> "Full Fate";
            case SLIGHT_MADNESS -> "Slight Madness";
        };
    }
}
