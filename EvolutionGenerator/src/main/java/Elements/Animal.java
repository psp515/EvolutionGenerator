package Elements;

import Exceptions.InvalidArgumentExcetpion;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IGenes;
import Interfaces.Map.IMap;
import Tools.Vector2d;

public class Animal extends MapElement {

    private final IAnimalMovement _movement;
    public final IGenes _genotype;
    private int energy;
    private int deathDay;
    private boolean isDead = false;
    private int foodEaten;
    private int childrens;

    public boolean isHighlighted;

    public Animal(IMap map, Vector2d position, IGenes genotype, IAnimalMovement movement, int bornDay, int startingEnergy) {
        super(map, position, bornDay);

        _genotype = genotype;
        _movement = movement;

        energy = startingEnergy;
        deathDay = -1;
        foodEaten = 0;
        childrens = 0;
        isDead = false;
    }

    public void eat(Food food){

        if(food._map != this._map)
            return;

        int foodEnergy = this._map.getMapSettings().energyFromFood;
        int maxEnergy = this._map.getMapSettings().maxEnergy;

        if( energy + maxEnergy > maxEnergy)
            energy = maxEnergy;
        else
            energy += foodEnergy;

        _map.removeElement(food);
    }

    public void move(){

        //TODO gdy będą geny


    }

    public Animal copulate(Animal secondParent, int day) {




        // tworzennie nowego genu
        // tworzenie nowego animala
        // wszystkie potrzebne typy albo modyfikacje mozna odczytac z map settings.

        return null;
    }

    public void setDeathDay(int day) throws InvalidArgumentExcetpion {

        if (isDead)
            return;

        if(day < this._creationDay)
            throw new InvalidArgumentExcetpion();

        isDead = true;
        deathDay = day;
    }

    public int getDeathDay(){
        return this.deathDay;
    }

    public int getChildrensCount(){
        return this.childrens;
    }
    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString()
    {
        return RESOURCES_STRING + "/animals/" + getAnimalImage();
    }

    private String getAnimalImage()
    {
        int number = (int) Math.floor(energy / _map.getMapSettings().maxEnergy);
        return "a"+number+".png";
    }
}
