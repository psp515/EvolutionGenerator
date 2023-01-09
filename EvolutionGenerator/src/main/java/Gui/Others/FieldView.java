package Gui.Others;

import Elements.Animal;
import Interfaces.Map.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

//TODO narazie wstawione zeby pamiętać
public class FieldView {
    private Image image;
    private ImageView view;
    private Label label;

    public VBox container;

    public FieldView(IMapElement strongestAnimal, int animalsCount, IMapElement food, ArrayList<Animal> animals)  {

        this.container = new VBox();
        this.container.setMinHeight(30);
        this.container.setMinWidth(30);
        this.container.setMaxHeight(30);
        this.container.setMinWidth(30);

        if(strongestAnimal == null)
        {
            if(food != null){
                try
                {
                    this.image = new Image( new FileInputStream(food.getImage()));
                    this.view = new ImageView(this.image);
                    this.view.setFitHeight(20);
                    this.view.setFitWidth(20);

                    this.label = new Label("0 A");
                    this.container = new VBox();
                    this.container.getChildren().add(this.view);
                    this.container.getChildren().add(this.label);
                    this.container.setAlignment(Pos.CENTER);

                }
                catch (FileNotFoundException e) {}


            }
        }
        else
        {
            try
            {
                this.image = new Image( new FileInputStream(strongestAnimal.getImage()));
                this.view = new ImageView(this.image);
                this.view.setFitHeight(20);
                this.view.setFitWidth(20);

                this.label = new Label(String.valueOf(animalsCount)+"A");
                this.container.getChildren().add(this.view);
                this.container.getChildren().add(this.label);
                this.container.setAlignment(Pos.CENTER);

                if(animals.stream().anyMatch(animal -> animal.isHighlighted))
                {
                    this.container.setStyle("-fx-background-color: #90EE90;");
                }

            }
            catch (FileNotFoundException e) {}

        }
    }
}
