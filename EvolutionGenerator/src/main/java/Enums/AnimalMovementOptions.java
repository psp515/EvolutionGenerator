package Enums;

import ElementsExtensions.AnimalMovement.FullFate;
import ElementsExtensions.AnimalMovement.SlightMadness;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Others.GetClassRepresentation;

public enum AnimalMovementOptions implements GetClassRepresentation<IAnimalMovement> {
    DEFAULT,
    FULL_FATE,
    SLIGHT_MADNESS;

    @Override
    public IAnimalMovement getClassRepresentation() {
        return switch (this) {
            case FULL_FATE, DEFAULT -> new FullFate();
            case SLIGHT_MADNESS -> new SlightMadness();
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
