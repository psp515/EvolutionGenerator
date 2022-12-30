package ElementsExtensions.Genes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class NormalGenotype extends Genes{
    public NormalGenotype(int[] genes) {
        super(genes);
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
