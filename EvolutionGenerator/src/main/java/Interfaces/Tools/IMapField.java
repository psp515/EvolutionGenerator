package Interfaces.Tools;

import Gui.VieldView;
import Interfaces.Map.IMapElement;

import java.io.FileNotFoundException;

public interface IMapField
{
    boolean addElement(IMapElement element);
    boolean removeMapElement(IMapElement element);
    IMapElement[] getElements();
    VieldView getView() throws FileNotFoundException;
}
