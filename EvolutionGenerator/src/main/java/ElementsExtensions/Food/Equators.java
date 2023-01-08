package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
<<<<<<< Updated upstream
import Tools.SingleFoodField;
=======
import Interfaces.Map.IMapElement;
import Interfaces.Tools.IFoodField;
import Interfaces.Tools.IMapField;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;
>>>>>>> Stashed changes

public class Equators extends FoodGenerator {


<<<<<<< Updated upstream
    public Equators(SingleFoodField[][] mapField) {
        super(mapField);
    }

    @Override
    public Food growFood() {
        return null;
=======

    public Equators(SingleFoodField[][] mapField, MapStatistics statistics) {
        super(mapField, statistics);
    }

    @Override
    public Food growFood(IMap map, int day) {

        Vector2d endpoint = map.getEndBound();
        int equatorheight = (int) (Math.round((endpoint.y +1)/5.0));
        int equatorStartY = endpoint.y/2 - 1;

        // tablice z uporządkowanymi indeksami Xowymi i Yowymi zależnie od stref:
        // wszystkie Xowe, wszystkie Yowe, wszystkie nieurodzajne, nad równikiem, równik, pod równikiem

        ArrayList<Integer> indexesX = new ArrayList<>();
        for(int i = 0; i <= endpoint.x; i++)
            indexesX.add(i);
        Collections.shuffle(indexesX);

        ArrayList<Integer> indexesY = new ArrayList<>();
        ArrayList<Integer> indexesYdry = new ArrayList<>();
        ArrayList<Integer> indexesYequator = new ArrayList<>();

        for(int i = 0; i < equatorStartY; i++)
            indexesYdry.add(i);
        for(int i=equatorStartY+equatorheight; i <= endpoint.y; i++)
            indexesYdry.add(i);
        Collections.shuffle(indexesYdry);

        for(int i=equatorStartY; i < equatorStartY+equatorheight; i++)
            indexesYequator.add(i);
        Collections.shuffle(indexesYequator);





        // potasowanie pól tablic wg rodzaju pola  i zlepienie tych tablic po kolei wg wylosowanej kolejności
        // (w końcu jak w wylosowanej części nie będzie miejsc wolnych to przepatrujemy inne)

        int fieldType = ThreadLocalRandom.current().nextInt(5);  // jak 0 to nieurodzajne

        if(fieldType == 0){
            indexesY.addAll(indexesYdry);
            indexesY.addAll(indexesYequator);
        }
        else{
            indexesY.addAll(indexesYequator);
            indexesY.addAll(indexesYdry);
        }

        // przepatrywanie pól po kolei czy nie ma już Food wg wylosowanej wcześniej kolejności indexów Xowych i Yowych

        int xFind = 0;
        int yFind = 0;

        while(true){

            if(yFind == endpoint.y + 1)
                return null;

            IMapField field = map.getMapFields()[indexesX.get(xFind)][indexesY.get(yFind)];
            if(field.containsFood()){
                if(xFind < endpoint.x)
                    xFind++;
                else{
                    xFind = 0;
                    yFind++;
                }
            }
            else
                break;
        }

        int x = indexesX.get(xFind);
        int y = indexesY.get(yFind);

        return new Food(map, new Vector2d(x, y), day);
>>>>>>> Stashed changes
    }

}
