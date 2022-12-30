package ElementsExtensions.Genes;
import Elements.Animal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class NormalGenotype extends Genes{
    public NormalGenotype(int[] genes) {
        super(genes);
    }

    public NormalGenotype(Animal p1, Animal p2, int len) {
        super(p1,p2,len);
    }

    public void mutate(){
        int changedGenesCount = ThreadLocalRandom.current().nextInt(this.getGenLength());
        ArrayList<Integer> genesToChange = new ArrayList<>();
        for(int i=0; i<this.getGenLength(); i++){
            genesToChange.add(i);
        }
        Collections.shuffle(genesToChange);

        for(int i=0; i<changedGenesCount; i++){
            this.getGenes()[genesToChange.get(i)] = ThreadLocalRandom.current().nextInt(8);
        }
    }
}
