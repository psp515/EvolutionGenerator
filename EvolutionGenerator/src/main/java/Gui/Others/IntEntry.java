package Gui.Others;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class IntEntry extends VBox {

    NumberTextField input;
    public final int min;
    public final int max;
    public IntEntry(String description, int initialValue, int min, int max){

        this.getChildren().add(new Label(description));
        input = new NumberTextField();
        input.setText(String.valueOf(initialValue));
        this.getChildren().add(input);
        this.setPadding(new Insets(4,10,4,10));

        this.max = max;
        this.min = min;
    }


    public int getValue(){
        return this.input.getValue();
    }

    public void setValue(int value)
    {
        this.input.setText(String.valueOf(value));
    }

    public boolean isValid(){
        return this.getValue() >= this.min && this.getValue() <= this.max;
    }
}
