package Tools;

import Elements.Animal;
import Elements.Food;
import Gui.Others.FieldView;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IFoodField;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

public class SingleFoodField extends MapField implements IFoodField {
    private IMapElement food;

    private ArrayList<Animal> elements;

    public SingleFoodField() {
        // tylko zwierzeta przechowywane
        elements = new ArrayList<>();
    }

    public boolean addElement(IMapElement element){

        if(element instanceof Food) {
            if(food != null)
                return false;

            food = element;
            return true;
        }

        elements.add((Animal) element);
        return true;
    }

    public boolean removeMapElement(IMapElement element){

        if(element instanceof Food) {
            if(food == null)
                return false;

            food = null;
            return true;
        }

        elements.remove((Animal)  element);
        return true;
    }

    public IMapElement[] getElements(){

        var copy = new ArrayList<IMapElement>(elements);

        if(food != null)
            copy.add(food);

        return copy.toArray(IMapElement[]::new);
    }

    public Animal getStrongestAnimal(){
        return  elements.stream().max(Comparator.comparing(Animal::getEnergy)).orElse(null);
    }

    @Override
    public FieldView getView() throws FileNotFoundException {
        return new FieldView(getStrongestAnimal(),elements.size(),food != null);
    }

    public ArrayList<Animal> getAnimals(){
        return new ArrayList<Animal>(elements);
    }

    @Override
    public boolean containsFood() {
        return food != null;
    }

    @Override
    public Food getFood() {
        return (Food) food;
    }
}