package Tools;

import Interfaces.Map.IMapElement;

public class MapField
{
    public IMapElement[] elementsOnField;
    public final Vector2d position;

    public MapField(Vector2d position)
    {
        this.position = position;
    }

    //TODO można tu wzucić wygenerowanie widoku pola.
}
