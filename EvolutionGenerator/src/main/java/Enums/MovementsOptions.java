package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Others.EnumClassRepresentation;

public enum MovementsOptions implements EnumClassRepresentation<IAnimalMovement> {
    FullFate,
    SlightMadness;

    @Override
    public IAnimalMovement getClassRepresentation() {
        return switch (this) {
            case FullFate -> new ElementsExtensions.AnimalMovement.FullFate();
            case SlightMadness -> new ElementsExtensions.AnimalMovement.SlightMadness();
        };
    }

    //TODO : to string -> nazwy
}
