package Elements;

import Enums.MapDirection;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IGenes;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapMoveElement;
import Models.MapSettings;
import Tools.Vector2d;
import javafx.scene.control.ComboBox;
import javafx.util.Pair;


public class Animal extends MapElement implements IMapMoveElement, Comparable<Animal> {

    private final IAnimalMovement _movement;
    public final IGenes _genotype;
    private int energy;
    private int deathDay;
    private boolean isDead;
    private int foodEaten;
    private int children;

    private MapDirection actDirection;

    public boolean isGenotypeHighlighted;

    public boolean isHighlighted;

    public Animal(IMap map, Vector2d position, IGenes genotype, IAnimalMovement movement, int bornDay, int startingEnergy) {
        super(map, position, bornDay);

        actDirection = MapDirection.WEST;

        _genotype = genotype;
        _movement = movement;

        energy = startingEnergy;
        deathDay = -1;
        foodEaten = 0;
        children = 0;
        isDead = false;
    }

    public void eat(Food food){

        if(food._map != this._map)
            return;

        int foodEnergy = this._map.getMapSettings().energyFromFood;
        int maxEnergy = this._map.getMapSettings().maxEnergy;

        if( energy + foodEnergy > maxEnergy)
            energy = maxEnergy;
        else
            energy += foodEnergy;

        foodEaten += 1;

        _map.removeElement(food);
    }

    public Animal copulate(Animal secondParent, int day) {
        MapSettings settings = this._map.getMapSettings();

        if (this.energy < settings.copulationMinimalEnergy || secondParent.getEnergy() < settings.copulationMinimalEnergy)
            return null;

        this.useEnergy(settings.copulationCostEnregy);
        secondParent.useEnergy(settings.copulationCostEnregy);

        this.childrenBorn();
        secondParent.childrenBorn();

        IGenes genes = settings.geneOptions.getClassRepresentation(this, secondParent, settings.gensLength);

        Animal youngling = new Animal(
                this._map,
                this.position.copy(),
                genes,
                settings.movementsOptions.getClassRepresentation(),
                day,
                settings.copulationCostEnregy * 2);

        _map.placeElement(youngling);

        return youngling;
    }
    public void setDeathDay(int day) throws IllegalArgumentException {

        if (isDead)
            return;

        if(day < this._creationDay)
            throw new IllegalArgumentException("Death day must be bigger than creation day.");

        isDead = true;
        deathDay = day;
    }
    public int getDeathDay(){
        return this.deathDay;
    }
    public int getEatenFood(){
        return this.foodEaten;
    }
    public int getChildrensCount(){
        return this.children;
    }
    public void childrenBorn(){
        this.children +=1;
    }
    @Override
    public void move(){
        Vector2d newPosition = _movement.nextPosition(_genotype, position, actDirection);
        Pair<MapDirection, Vector2d> pair = _map.moveElement(this,position, newPosition);

        this.position = pair.getValue();
        this.actDirection = pair.getKey();
    }
    @Override
    public void useEnergy(int energy){
        this.energy = Math.max(0, this.energy - energy);
    }
    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public String getImage()
    {
        return RESOURCES_STRING + "/animals/" + getAnimalImage();
    }
    private String getAnimalImage() {
        int img = (int) Math.floor(energy * 6 / _map.getMapSettings().maxEnergy);
        return "a"+img+".png";
    }

    @Override
    public int compareTo(Animal cmp) {

        //A energia
        if(cmp.getEnergy() > this.getEnergy())
            return -1;
        else if (this.getEnergy() > cmp.getEnergy())
            return 1;

        //B urodzenie
        if(cmp._creationDay < this._creationDay)
            return -1;
        else if (cmp._creationDay > this._creationDay)
            return 1;

        //C dzieci
        if(cmp.getChildrensCount() > this.getChildrensCount())
            return -1;
        else if (cmp.getChildrensCount() < this.getChildrensCount())
            return 1;

        //D losowo
        return Math.random() >= 0.5 ? 1 : -1;
    }
}
