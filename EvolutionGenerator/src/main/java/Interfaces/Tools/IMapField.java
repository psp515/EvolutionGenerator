package Interfaces.Tools;

import Gui.Others.FieldView;
import Interfaces.Map.IMapElement;

import java.io.FileNotFoundException;

public interface IMapField
{
    boolean addElement(IMapElement element);
    boolean removeMapElement(IMapElement element);
    IMapElement[] getElements();
    FieldView getView() throws FileNotFoundException;
}
