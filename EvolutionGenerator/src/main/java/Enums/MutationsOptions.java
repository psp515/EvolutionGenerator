package Enums;

import ElementsExtensions.Food.DeathBodies;
import ElementsExtensions.Food.Equators;
import Interfaces.Animals.IAnimalMutation;
import Interfaces.Others.EnumClassRepresentation;

public enum MutationsOptions implements EnumClassRepresentation<IAnimalMutation> {
    Mutate,
    SlightMutate;

    @Override
    public IAnimalMutation getClassRepresentation() {
        return switch (this) {
            case Mutate -> new ElementsExtensions.AnimalMutations.Mutate();
            case SlightMutate -> new ElementsExtensions.AnimalMutations.SlightMutate();
        };
    }

    //TODO : to string -> nazwy

}
