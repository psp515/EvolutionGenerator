package Elements;

import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IGenes;
import Interfaces.Animals.IParent;
import Interfaces.Map.IMap;
import Tools.Vector2d;

public class Animal extends MapElement implements IParent {

    private final IAnimalMovement _movement;
    public final IGenes _genotype;
    private int _energy;

    private int bornDay;
    private int deathDay;
    private int foodEaten;

    private boolean MostPopularGeonotype;

    public Animal(IMap map, IGenes genotype, int startingEnergy, IAnimalMovement movement, Vector2d position, int bornDay) {
        super(map, position);
        _genotype = genotype;
        _energy = startingEnergy;
        _movement = movement;
    }

    public int get_energy() {
        return _energy;
    }
    public void eatFood(){

    }

    public void move(){
        // tu uzywamy move element z map jezeli zwróci true to mozemy zamienic jezeli nie to nie zamieniamy
    }

    @Override
    public IParent copulate(IParent secondParent, int day) {
        // tworzennie nowego genu
        // tworzenie nowego animala
        // wszystkie potrzebne typy albo modyfikacje mozna odczytac z map settings.

        return null;
    }

    @Override
    public int getNumberOfChildrens(){return 0;}
}
