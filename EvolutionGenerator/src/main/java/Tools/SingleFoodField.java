package Tools;

import Elements.Food;
import Gui.VieldView;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IFoodField;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class SingleFoodField extends MapField implements IFoodField {
    private IMapElement food;

    public SingleFoodField() {
        // elements for animals only
        elements = new ArrayList<>();
    }

    public boolean addElement(IMapElement element){

        if(element instanceof Food) {
            if(food != null)
                return false;

            food = element;
            return true;
        }

        elements.add(element);
        return true;
    }

    public boolean removeMapElement(IMapElement element){

        if(element instanceof Food) {
            if(food == null)
                return false;

            food = null;
            return true;
        }

        elements.remove(element);
        return true;
    }

    public IMapElement[] getElements(){
        //returns only animals
        return elements.toArray(IMapElement[]::new);
    }

    @Override
    public VieldView getView() throws FileNotFoundException {
        //TODO strongest animal or food or null.

        return new VieldView(null,0,false);
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
