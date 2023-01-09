package Tools;

import Elements.Food;
import Gui.Others.FieldView;
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IMapField;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class MapField implements IMapField
{
    protected ArrayList<IMapElement> elements;

    public MapField() {
        elements = new ArrayList<>();
    }

    @Override
    public boolean addElement(IMapElement element){

        elements.add(element);
        return true;
    }
    @Override
    public boolean removeMapElement(IMapElement element){

        elements.remove(element);
        return true;
    }
    @Override
    public IMapElement[] getElements(){
        return elements.toArray(IMapElement[]::new);
    }

    @Override
    public FieldView getView() throws FileNotFoundException {
        FieldView view;

        if (elements.isEmpty())
            return new FieldView(null,0,null, null);

        IMapElement food =  elements.stream().filter(p->{return p instanceof Food;}).findFirst().orElse(null);

        return new FieldView(elements.get(0),0,food, null);
    }
}
