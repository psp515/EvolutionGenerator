package Elements;

import ElementsExtensions.AnimalMovement.FullFate;
import ElementsExtensions.Genes.NormalGenotype;
import Enums.MapDirection;
import Interfaces.Animals.IAnimalMovement;
import Interfaces.Animals.IGenes;
import Tools.Vector2d;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.lang.System.out;

public class MovementTest {


    @Test
    public void FullFateTest(){
        Vector2d newVector = new Vector2d(0,0);
        IAnimalMovement movement = new FullFate();
        Vector2d position = new Vector2d(2,2);
        int[] gen = new int[] {1, 4, 2, 3, 4, 5, 6, 3, 2, 5, 1, 3 , 4, 3, 2, 4, 1, 5, 2, 4};
        IGenes genes = new NormalGenotype(gen);
        MapDirection direction = MapDirection.NORTH;

        out.println(position);
        out.println("\n");
        out.println(Arrays.toString(genes.getGenes()));
        out.println("\n");

        for(int i=0; i<10; i++) {
            newVector = movement.nextPosition(genes, position, direction).getValue();
            direction = movement.nextPosition(genes, position, direction).getKey();
            out.println(genes.getActiveGene());
            out.println(direction);
            position.x = newVector.x;
            position.y = newVector.y;
            out.println(position);
            out.println("\n");
        }

    }




}
