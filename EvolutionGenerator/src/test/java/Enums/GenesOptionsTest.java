package Enums;

import ElementsExtensions.Genes.DiscouragedGenotype;
import ElementsExtensions.Genes.NormalGenotype;
import Interfaces.OptionEnumTestMethods;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GenesOptionsTest extends BaseEnumTestClass<GenesOptions> implements OptionEnumTestMethods {

    @Override @Test
    public void toStringTest(){

        String[] answers = {
                "Default",
                "Normal Genotype",
                "Discouraged Genotype"};

        assertTrue(toStringTest(GenesOptions.values(), answers));
    }

    @Override @Test
    public void getClassRepresentationTest() {
        assertTrue(GenesOptions.DEFAULT.getClassRepresentation(null) instanceof NormalGenotype);
        assertTrue(GenesOptions.NORMAL_GENOTYPE.getClassRepresentation(null) instanceof NormalGenotype);
        assertTrue(GenesOptions.DISCOURAGED_GENOTYPE.getClassRepresentation(null) instanceof DiscouragedGenotype);
    }
}
