package Tools;

import Gui.VieldView;
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
    public VieldView getView() throws FileNotFoundException {
        VieldView view;

        if (elements.isEmpty())
            return new VieldView(null,0,false);

        return new VieldView(elements.get(0),0,false);
    }
}
