package ElementsExtensions.Food;

import Elements.Food;
import Interfaces.Map.IMap;
import Models.MapStatistics;
import Tools.SingleFoodField;
import Tools.Vector2d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.pow;

public class DeathBodies extends FoodGenerator {

    public int[][] deathlist;

    public DeathBodies(SingleFoodField[][] mapField, MapStatistics statistics) {
        super(mapField);
        this.deathlist = statistics.animalDeathsOnField;
    }

    @Override
    public Food growFood(IMap map, int day) {

        // common variables
        Vector2d startpoint = map.getStartBound();
        Vector2d endpoint = map.getEndBound();
        int squaresCount = (endpoint.x- startpoint.x+1)*(endpoint.y- startpoint.y+1);
        double xLength = (Math.log10(endpoint.x) + 1) + 1;
        double yLength = (Math.log10(endpoint.y) + 1) + 1;

        // hashowanie tablicy pól zgon/x/y żeby było integerem + posortowanie pól wg zgonów
        ArrayList<Integer> squaresPrefOrder = new ArrayList<>();
        for(int i=startpoint.x; i<=endpoint.x;i++){
            for(int j= startpoint.y; j<=endpoint.y; j++){
                int temp = (int) (deathlist[i][j]*pow(10,xLength+yLength) + i*pow(10,yLength) + j);
                squaresPrefOrder.add(temp);
            }
        }
        Collections.sort(squaresPrefOrder);

        // osobne tablice dla preferowanych i niepreferowanych żeby potem je poshufflować
        ArrayList<Integer> prefPoints = new ArrayList<>();
        ArrayList<Integer> restPoints = new ArrayList<>();


        int preferredLength = squaresCount/5;
        int isPreffered = ThreadLocalRandom.current().nextInt(5);  // jak 0 to nieurodzajne

        // dodanie i poshufflowanie osobno preferowanych i niepreferowanych i złączenie w jedną tablicę
        for(int i=0; i<preferredLength; i++){
            prefPoints.add(squaresPrefOrder.get(i));
        }
        for(int i=preferredLength; i<squaresCount; i++){
            restPoints.add(squaresPrefOrder.get(i));
        }
        Collections.shuffle(squaresPrefOrder);
        Collections.shuffle(restPoints);
        if(isPreffered == 0)
            restPoints.addAll(prefPoints);
        else
            prefPoints.addAll(restPoints);


        // znalezienie wolnego i najbardziej odpowiadającego programowi miejsca
        int xIndex;
        int yIndex;
        int i = 0;
        do {

            if (isPreffered == 0) {
                xIndex = restPoints.get(i) / (int) (pow(10, yLength));
                xIndex = (int) (xIndex % pow(10, yLength));
                yIndex = (int) (restPoints.get(i) % pow(10, yLength));
            } else {
                xIndex = prefPoints.get(i) / (int) (pow(10, yLength));
                xIndex = (int) (xIndex % pow(10, yLength));
                yIndex = (int) (prefPoints.get(i) % pow(10, yLength));
            }

            if(!this.field[xIndex][yIndex].containsFood())
                return new Food(map, new Vector2d(xIndex, yIndex), day);
            i++;

        }while(i<squaresCount);



        return null;
    }

}
