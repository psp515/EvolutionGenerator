package ElementsExtensions.Genes;

import Elements.Animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class DiscouragedGenotype extends Genes {
    public DiscouragedGenotype(int[] genes) {
        super(genes);
    }

    public DiscouragedGenotype(Animal p1, Animal p2, int len) {
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
            this.getGenes()[genesToChange.get(i)] += 1-ThreadLocalRandom.current().nextInt(3);
            if(this.getGenes()[genesToChange.get(i)] == -1)
                this.getGenes()[genesToChange.get(i)] = 7;
            else if(this.getGenes()[genesToChange.get(i)] == 8)
                this.getGenes()[genesToChange.get(i)] = 0;
        }
    }
}
