package Enums;

public abstract class BaseEnumTestClass<Enum> {

    protected boolean toStringTest(Enum[] values, String[] answers){

        if(values.length != answers.length)
            return false;

        boolean valid = true;

        for(int i = 0; i < values.length;i++)
            valid = valid && answers[i].equals(values[i].toString());

        return valid;
    }
}
