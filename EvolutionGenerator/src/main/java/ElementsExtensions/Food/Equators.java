package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Interfaces.Map.IMapElement;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Equators extends FoodGenerator {




    public Equators(SingleFoodField[][] mapField) {
        super(mapField);
    }

    @Override
    public Food growFood(IMap map, int day) {

        //TODO FIX: tutaj gdzies robi się infinite loop

        Vector2d startpoint = map.getStartBound();
        Vector2d endpoint = map.getEndBound();
        int equatorheight = (endpoint.y - startpoint.y)/5;
        int equatorStartY = startpoint.y + equatorheight;

        // tablice z uporządkowanymi indeksami Xowymi i Yowymi zależnie od stref:
        // wszystkie Xowe, wszystkie Yowe, wszystkie nieurodzajne, nad równikiem, równik, pod równikiem

        ArrayList<Integer> indexesX = new ArrayList<>();
        for(int i=startpoint.x; i<endpoint.x; i++)
            indexesX.add(i);
        Collections.shuffle(indexesX);

        ArrayList<Integer> indexesY = new ArrayList<>();
        ArrayList<Integer> indexesYdry = new ArrayList<>();

        ArrayList<Integer> indexesYupper = new ArrayList<>();
        for(int i=startpoint.y; i<equatorStartY; i++)
            indexesYupper.add(i);
        Collections.shuffle(indexesYupper);

        ArrayList<Integer> indexesYequator = new ArrayList<>();
        for(int i=equatorStartY; i<equatorStartY+equatorheight; i++)
            indexesYequator.add(i);
        Collections.shuffle(indexesYequator);

        ArrayList<Integer> indexesYbottom = new ArrayList<>();
        for(int i=equatorStartY+equatorheight; i<endpoint.y; i++)
            indexesYbottom.add(i);
        Collections.shuffle(indexesYbottom);



        // potasowanie pól tablic wg rodzaju pola  i zlepienie tych tablic po kolei wg wylosowanej kolejności
        // (w końcu jak w wylosowanej części nie będzie miejsc wolnych to przepatrujemy inne)

        int fieldType = ThreadLocalRandom.current().nextInt(5);  // jak 0 to nieurodzajne
        if(fieldType == 0){

            int opt = ThreadLocalRandom.current().nextInt(2);
            if(opt == 0) {
                indexesY.addAll(indexesYupper);
                indexesY.addAll(indexesYbottom);
                indexesYbottom.addAll(indexesYequator);
            }
            else{
                indexesY.addAll(indexesYbottom);
                indexesY.addAll(indexesYupper);
                indexesYbottom.addAll(indexesYequator);
            }
        }
        else{
            indexesYdry.addAll(indexesYbottom);
            indexesYdry.addAll(indexesYupper);
            Collections.shuffle(indexesYdry);
            indexesY.addAll(indexesYequator);
            indexesY.addAll(indexesYdry);
        }

        // przepatrywanie pól po kolei czy nie ma już Food wg wylosowanej wcześniej kolejności indexów Xowych i Yowych

        int xFind = 0;
        int yFind = 0;
        int flag = 0;           // 0-status quo, 1-znalezione pole zajęte już przez Food
        while(true){

            if(yFind == endpoint.y)
                return null;

            IMapElement[] elements = map.getMapFields()[indexesX.get(xFind)][indexesY.get(yFind)].getElements();
            for(IMapElement element:elements){
                if(element instanceof Food) {
                    flag = 1;
                    break;
                }
            }

            if(flag==1){
                if(xFind < endpoint.x) {
                    xFind++;
                    flag = 0;
                }
                else{
                    xFind = 0;
                    yFind++;
                    flag = 0;
                }
            }
            else
                break;
        }

        int x = indexesX.get(xFind);
        int y = indexesY.get(yFind);

        return new Food(map, new Vector2d(x, y), day);
    }

}